package DAO.livro;

import DAO.CRUD;
import model.Livro;

import java.util.List;

interface LivroDAO extends CRUD<Livro> {

    public List<Livro> findByAutor(String autor);
    public List<Livro> findByTitulo(String titulo);
    public List<Livro> findByCategoria(String categoria);
    public List<Livro> findByISBN(Integer ISBN);

}
