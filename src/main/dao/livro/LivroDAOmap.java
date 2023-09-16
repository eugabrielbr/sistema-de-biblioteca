package main.dao.livro;

import main.exceptions.livro.LivroExceptions;
import main.model.Livro;

import java.util.*;
import java.util.Map.Entry;

public class LivroDAOmap implements LivroDAO {

    private Integer ID = 0;
    public Map<Integer,Livro> acervo = new HashMap<>();

    public List<Livro> findByAutor(String autor) throws LivroExceptions{


        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getAutor(), autor)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new LivroExceptions(LivroExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }

    }

    public List<Livro> findByTitulo(String titulo) throws LivroExceptions{

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getTitulo(), titulo)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new LivroExceptions(LivroExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }

    public List<Livro> findByCategoria(String categoria) throws LivroExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getCategoria(), categoria)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new LivroExceptions(LivroExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }

    public List<Livro> findByISBN(Integer ISBN) throws LivroExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getISBN(), ISBN)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new LivroExceptions(LivroExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }


    @Override
    public void create(Livro livro){

        ID++;
        livro.setID(ID);
        acervo.put(ID,livro);

    }

    @Override
    public void delete(int ID) throws LivroExceptions {

        Livro livro = acervo.remove(ID);

        if (livro == null){
            throw new LivroExceptions(LivroExceptions.DELETE,ID);
        }


    }

    @Override
    public void deleteMany() throws LivroExceptions {

        this.acervo = new HashMap<>();
        this.ID = 0;

    }

    @Override
    public void update(Livro livro, Integer ID) throws LivroExceptions {

        Livro get = acervo.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            acervo.put(newId, livro);

        }
        else{
            throw new LivroExceptions(LivroExceptions.UPDATE,ID);
        }


    }

    @Override
    public Map<Integer,Livro> findMany() {

        return acervo;

    }

    @Override
    public Livro findById(int id) throws LivroExceptions{
        Livro busca = acervo.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new LivroExceptions(LivroExceptions.NOT_FOUND,id);
        }

    }




}
