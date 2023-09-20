package main.dao;

import main.dao.administrador.AdministradorDAO;
import main.dao.administrador.AdministradorDAOmap;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.emprestimo.EmprestimoDAOmap;
import main.dao.livro.LivroDAOmap;
import main.dao.usuario.UsuarioDAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAOmap;

public class DAO {


    private static LivroDAO livroDAO;
    private static UsuarioDAO usuarioDAO;
    private static AdministradorDAO administradorDAO;
    private static EmprestimoDAO emprestimoDAO;

    public static LivroDAO getLivroDAO() {
        if (livroDAO == null){
            livroDAO = new LivroDAOmap();
        }
        return livroDAO;
    }

    public static UsuarioDAO getUsuarioDAO() {

        if (usuarioDAO == null){
            usuarioDAO = new UsuarioDAOmap();

        }
        return usuarioDAO;

    }

    public AdministradorDAO getAdministradorDAO() {

        if (administradorDAO == null){
            administradorDAO = new AdministradorDAOmap();
        }

        return administradorDAO;
    }

    public static EmprestimoDAO getEmprestimoDAO() {

        if (emprestimoDAO == null) {
            emprestimoDAO = new EmprestimoDAOmap();
        }
        return emprestimoDAO;
    }
}
