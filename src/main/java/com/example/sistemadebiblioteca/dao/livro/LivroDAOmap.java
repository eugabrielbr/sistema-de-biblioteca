package main.java.com.example.sistemadebiblioteca.dao.livro;

import main.java.com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import main.java.com.example.sistemadebiblioteca.model.Livro;

import java.util.*;
import java.util.Map.Entry;

/**
 * DAO da classe Livro
 * @author Gabriel
 */
public class LivroDAOmap implements LivroDAO {

    /**
     * contador para ID
     */
    private Integer ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer,Livro> acervo;

    public LivroDAOmap(){

        this.ID = 0;
        this.acervo = new HashMap<>();
    }

    /**
     * busca livros e retorna lista de livros de acordo com o autor
     * @param autor autor do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    public List<Livro> findByAutor(String autor) throws DAOExceptions {


        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getAutor(), autor)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }

    }
    /**
     * busca livros e retorna lista de livros de acordo com o titulo
     * @param titulo titulo do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    public List<Livro> findByTitulo(String titulo) throws DAOExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getTitulo(), titulo)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }
    /**
     * busca livros e retorna lista de livros de acordo com a categoria
     * @param categoria categoria do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    public List<Livro> findByCategoria(String categoria) throws DAOExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getCategoria(), categoria)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }
    /**
     * busca livros e retorna lista de livros de acordo com o ISBN
     * @param ISBN ISBN do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    public List<Livro> findByISBN(Integer ISBN) throws DAOExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getISBN(), ISBN)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }

    /**
     * registra livro
     * @param livro objeto livro
     */
    @Override
    public void create(Livro livro){

        ID++;
        livro.setID(ID);
        acervo.put(ID,livro);

    }

    /**
     * deleta livros
     * @param ID id do livro
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void delete(int ID) throws DAOExceptions {

        Livro livro = acervo.remove(ID);

        if (livro == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }


    }

    /**
     * deleta todos os dados e zera o contador de id
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void deleteMany() throws DAOExceptions {

        this.acervo = new HashMap<>();
        this.ID = 0;

    }

    /**
     * atualiza um livro por outro
     * @param livro objeto livro
     * @param ID id do livro que vai ser substituido
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void update(Livro livro, Integer ID) throws DAOExceptions {

        Livro get = acervo.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            livro.setID(newId);
            acervo.put(newId, livro);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }


    }

    /**
     * retorna o map acervo
     * @return map de livros
     */
    @Override
    public Map<Integer,Livro> findMany() {

        return acervo;

    }

    /**
     * busca e retorna livro por id
     * @param id id do livro
     * @return livro buscado
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public Livro findById(int id) throws DAOExceptions {

        Livro busca = acervo.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,id);
        }

    }




}
