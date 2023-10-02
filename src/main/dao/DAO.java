package main.dao;


import main.dao.emprestimo.EmprestimoDAO;
import main.dao.emprestimo.EmprestimoDAOmap;
import main.dao.livro.LivroDAOmap;
import main.dao.operadores.OperadoresDAO;
import main.dao.operadores.OperadoresDAOmap;
import main.dao.usuario.UsuarioDAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAOmap;

/**
 * classe Singleton do dao
 * @author Gabriel
 */
public class DAO {

    /**
     * livroDAO objeto LivroDAO
     */
    private static LivroDAO livroDAO;
    /**
     * usuarioDAO objeto UsuarioDAO
     */
    private static UsuarioDAO usuarioDAO;
    /**
     * operadoresDAO objeto OperadoresDAO
     */
    private static OperadoresDAO operadoresDAO;
    /**
     * emprestimoDAO objeto EmprestimoDAO
     */
    private static EmprestimoDAO emprestimoDAO;

    /**
     * criar e retorna um objeto
     * @return objeto LivroDAOmap
     */
    public static LivroDAO getLivroDAO() {
        if (livroDAO == null){
            livroDAO = new LivroDAOmap();
        }
        return livroDAO;
    }
    /**
     * criar e retorna um objeto
     * @return objeto UsuarioDAOmap
     */
    public static UsuarioDAO getUsuarioDAO() {

        if (usuarioDAO == null){
            usuarioDAO = new UsuarioDAOmap();

        }
        return usuarioDAO;

    }
    /**
     * criar e retorna um objeto
     * @return objeto OperadoresDAOmap
     */
    public OperadoresDAO getOperadoresDAO() {

        if (operadoresDAO == null){
            operadoresDAO = new OperadoresDAOmap();
        }

        return operadoresDAO;
    }
    /**
     * criar e retorna um objeto
     * @return objeto EmprestimoDAOmap
     */
    public static EmprestimoDAO getEmprestimoDAO() {

        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAOmap();
        }
        return emprestimoDAO;
    }
}
