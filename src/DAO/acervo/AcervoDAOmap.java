package DAO.acervo;

import model.Livro;
import model.Pesquisa;

import java.util.*;
import java.util.Map.Entry;

public class AcervoDAOmap implements AcervoDAO {

    int ID = 1;
    public Map<Integer,Livro> acervo = new HashMap<>();

    public List<Livro> findByGeneral( Pesquisa pesquisa){ // faço a pesquisa por ID e jogo todos os objetos em uma lista, depois é fazer um filtro na pesquisa

        List<Livro> retornoBusca = new LinkedList<>();

        for (Entry<Integer, Livro> x : acervo.entrySet()){

            if (Objects.equals(pesquisa.getTitulo(), x.getValue().getTitulo()) || Objects.equals(pesquisa.getAutor(), x.getValue().getAutor()) || pesquisa.getISBN() == x.getValue().getISBN() || Objects.equals(pesquisa.getCategoria(), x.getValue().getCategoria())){

                Livro retorno = x.getValue();
                retornoBusca.add(retorno);

            }

        }
        return retornoBusca;
    }

    public Livro findByID(Map<Integer,Livro> dic,Integer ID){

        return dic.get(ID);
    }

    @Override
    public void create(Livro livro){

        acervo.put(ID,livro);
        ID++;


    }

    @Override
    public void delete(Livro obj) {

    }

    @Override
    public void deleteMany() {

    }

    @Override
    public Livro update(Livro obj) {
        return null;
    }

    @Override
    public Map<Integer, Livro> findMany() {
        return null;
    }

    @Override
    public Livro findById(int id) {
        return null;
    }

    public void delete(Integer chave){

        acervo.remove(chave);
    }


}
