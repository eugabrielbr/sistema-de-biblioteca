package DAO.livro;

import model.Livro;

import java.util.*;
import java.util.Map.Entry;

public class LivroDAOmap implements LivroDAO {

    int ID = 0;
    public Map<Integer,Livro> acervo = new HashMap<>();

    public List<Livro> findByAutor(String autor){

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getAutor(), autor)){
                lista.add(x.getValue());
            }
        }

        return lista;
    }

    public List<Livro> findByTitulo(String titulo){

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getTitulo(), titulo)){
                lista.add(x.getValue());
            }
        }

        return lista;
    }

    public List<Livro> findByCategoria(String categoria){

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getCategoria(), categoria)){
                lista.add(x.getValue());
            }
        }

        return lista;
    }

    public List<Livro> findByISBN(Integer ISBN){

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getISBN(), ISBN)){
                lista.add(x.getValue());
            }
        }

        return lista;
    }


    @Override
    public void create(Livro livro){

        ID++;
        livro.setID(ID);
        acervo.put(ID,livro);

    }

    @Override
    public boolean delete(int ID) {

        Livro livro = acervo.get(ID);

        if (livro != null) {
            return true;
        }
        else{
            return false;
        }


    }

    @Override
    public void deleteMany(List<Integer> lista){

        for (Integer x : lista){
            acervo.remove(x);
        }
    }

    @Override
    public Livro update(Livro livro) {
        return null;
    }

    @Override
    public Map<Integer, Livro> findMany() {
        return null;
    }

    @Override
    public Livro findById(int id) {
        Livro busca = acervo.get(id);
        return busca;
    }

    public void delete(Integer chave){

        acervo.remove(chave);
    }



}
