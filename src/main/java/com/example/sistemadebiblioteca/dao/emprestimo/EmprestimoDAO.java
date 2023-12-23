
package main.java.com.example.sistemadebiblioteca.dao.emprestimo;

import main.java.com.example.sistemadebiblioteca.dao.CRUD;
import main.java.com.example.sistemadebiblioteca.model.Emprestimo;
import main.java.com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;

import java.util.List;

/**
 * Interface EmprestimoDAO
 * @author Gabriel
 */
public interface EmprestimoDAO extends CRUD<Emprestimo> {

    /**
     * busca um objeto
     * @param IDuser ID do usuario
     * @return Lista<Emprestimo>
     */
    List<Emprestimo> findByUser( Integer IDuser);

    /**
     * busca um objeto
     * @param IDlivro ID do livro
     * @param IDusuario ID do usuario
     * @return Emprestimo
     * @throws DAOExceptions exceções do DAO
     */
    Emprestimo findByIDlivroIDusuario(Integer IDlivro, Integer IDusuario) throws DAOExceptions;
}
