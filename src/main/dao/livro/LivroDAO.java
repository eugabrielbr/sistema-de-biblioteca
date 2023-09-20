package main.dao.livro;

import main.dao.CRUD;
import main.exceptions.crud.CrudExceptions;
import main.model.Livro;

import java.util.List;

public interface LivroDAO extends CRUD<Livro> {

    public List<Livro> findByAutor(String autor) throws CrudExceptions;
    public List<Livro> findByTitulo(String titulo) throws CrudExceptions;
    public List<Livro> findByCategoria(String categoria) throws CrudExceptions;
    public List<Livro> findByISBN(Integer ISBN) throws CrudExceptions;

}
