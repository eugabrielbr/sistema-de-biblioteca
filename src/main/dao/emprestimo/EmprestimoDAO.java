

package main.dao.emprestimo;

import main.dao.CRUD;
import main.exceptions.dao.DAOExceptions;
import main.model.Emprestimo;

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
    List<Emprestimo> findByUser(Integer IDuser);

    /**
     * busca um objeto
     * @param IDlivro ID do livro
     * @param IDusuario ID do usuario
     * @return Emprestimo
     * @throws DAOExceptions exceções do DAO
     */
    Emprestimo findByIDlivroIDusuario(Integer IDlivro, Integer IDusuario) throws DAOExceptions;
}
