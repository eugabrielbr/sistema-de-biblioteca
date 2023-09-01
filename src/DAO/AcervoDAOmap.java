package DAO;

import model.Livro;

import java.util.*;
import java.util.Map.Entry;

public class AcervoDAOmap {

    public Map<Integer,Livro> acervo = new HashMap<>();

    public List<Livro> findByGeneral(Map<Integer,Livro> dic, String titulo, String autor, Integer ISBN, String categ){ // faço a pesquisa por ID e jogo todos os objetos em uma lista, depois é fazer um filtro na pesquisa

        List<Livro> retornoBusca = new LinkedList<>();

        for (Entry<Integer, Livro> x : dic.entrySet()){

            if (Objects.equals(titulo, x.getValue().getTitulo()) || Objects.equals(autor, x.getValue().getAutor()) || ISBN == x.getValue().getISBN() || Objects.equals(categ, x.getValue().getCategoria())){

                Livro retorno = x.getValue();
                retornoBusca.add(retorno);

            }

        }
        return retornoBusca;
    }

    public Livro findByID(Map<Integer,Livro> dic,Integer ID){

        return dic.get(ID);
    }
}
