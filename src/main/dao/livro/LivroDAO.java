package main.dao.livro;

import main.dao.CRUD;
import main.exceptions.livro.LivroExceptions;
import main.model.Livro;

import java.util.List;

public interface LivroDAO extends CRUD<Livro> {

    public List<Livro> findByAutor(String autor) throws LivroExceptions;
    public List<Livro> findByTitulo(String titulo) throws LivroExceptions;
    public List<Livro> findByCategoria(String categoria) throws LivroExceptions;
    public List<Livro> findByISBN(Integer ISBN) throws LivroExceptions;

}
