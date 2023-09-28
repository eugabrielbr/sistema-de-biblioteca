package main.dao.livro;

import main.dao.CRUD;
import main.exceptions.crud.DAOExceptions;
import main.model.Livro;

import java.util.List;

public interface LivroDAO extends CRUD<Livro> {

    public List<Livro> findByAutor(String autor) throws DAOExceptions;
    public List<Livro> findByTitulo(String titulo) throws DAOExceptions;
    public List<Livro> findByCategoria(String categoria) throws DAOExceptions;
    public List<Livro> findByISBN(Integer ISBN) throws DAOExceptions;

}
