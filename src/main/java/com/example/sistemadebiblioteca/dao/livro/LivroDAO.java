package main.java.com.example.sistemadebiblioteca.dao.livro;

import main.java.com.example.sistemadebiblioteca.dao.CRUD;
import main.java.com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import main.java.com.example.sistemadebiblioteca.model.Livro;

import java.util.List;

/**
 * Interface LivroDAO
 * @author Gabriel
 */
public interface LivroDAO extends CRUD<Livro> {

    /**
     * busca objeto(s)
     * @param autor autor do livro
     * @return List<Livro>
     * @throws DAOExceptions exceções do DAO
     */
    public List<Livro> findByAutor(String autor) throws DAOExceptions;
    /**
     * busca objeto(s)
     * @param titulo titulo do livro
     * @return List<Livro>
     * @throws DAOExceptions exceções do DAO
     */
    public List<Livro> findByTitulo(String titulo) throws DAOExceptions;
    /**
     * busca objeto(s)
     * @param categoria categoria do livro
     * @return List<Livro>
     * @throws DAOExceptions exceções do DAO
     */
    public List<Livro> findByCategoria(String categoria) throws DAOExceptions;
    /**
     * busca objeto(s)
     * @param ISBN ISBN do livro
     * @return livro de acordo com o ISBN
     * @throws DAOExceptions exceções do DAO
     */
    public List<Livro> findByISBN(Integer ISBN) throws DAOExceptions;

}
