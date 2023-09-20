package main.dao.livro;

import main.exceptions.crud.CrudExceptions;
import main.model.Livro;

import java.util.*;
import java.util.Map.Entry;

public class LivroDAOmap implements LivroDAO {

    private Integer ID;
    public Map<Integer,Livro> acervo;

    public LivroDAOmap(){

        this.ID = 0;
        this.acervo = new HashMap<>();
    }

    public List<Livro> findByAutor(String autor) throws CrudExceptions {


        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getAutor(), autor)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new CrudExceptions(CrudExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }

    }

    public List<Livro> findByTitulo(String titulo) throws CrudExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getTitulo(), titulo)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new CrudExceptions(CrudExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }

    public List<Livro> findByCategoria(String categoria) throws CrudExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getCategoria(), categoria)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new CrudExceptions(CrudExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }

    public List<Livro> findByISBN(Integer ISBN) throws CrudExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getISBN(), ISBN)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new CrudExceptions(CrudExceptions.NOT_FOUND,null);
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
    public void delete(int ID) throws CrudExceptions {

        Livro livro = acervo.remove(ID);

        if (livro == null){
            throw new CrudExceptions(CrudExceptions.DELETE,ID);
        }


    }

    @Override
    public void deleteMany() throws CrudExceptions {

        this.acervo = new HashMap<>();
        this.ID = 0;

    }

    @Override
    public void update(Livro livro, Integer ID) throws CrudExceptions {

        Livro get = acervo.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            acervo.put(newId, livro);

        }
        else{
            throw new CrudExceptions(CrudExceptions.UPDATE,ID);
        }


    }

    @Override
    public Map<Integer,Livro> findMany() {

        return acervo;

    }

    @Override
    public Livro findById(int id) throws CrudExceptions {
        Livro busca = acervo.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new CrudExceptions(CrudExceptions.NOT_FOUND,id);
        }

    }




}
