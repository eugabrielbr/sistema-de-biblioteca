package com.example.sistemadebiblioteca.dao;



import com.example.sistemadebiblioteca.dao.livro.ArquivoLivroDAO;
import com.example.sistemadebiblioteca.dao.livro.LivroDAO;
import com.example.sistemadebiblioteca.dao.livro.LivroDAOmap;
import com.example.sistemadebiblioteca.dao.operadores.ArquivoOperadoresDAO;
import com.example.sistemadebiblioteca.dao.operadores.OperadoresDAO;
import com.example.sistemadebiblioteca.dao.operadores.OperadoresDAOmap;
import com.example.sistemadebiblioteca.dao.usuario.ArquivoUsuarioDAO;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAO;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAOmap;
import com.example.sistemadebiblioteca.dao.emprestimo.ArquivoEmprestimoDAO;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAO;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAOmap;

import java.io.IOException;

/**
 * classe Singleton do dao
 * @author Gabriel
 */
public class DAO {

    /**
     * tipo de memoria que sera usada para armazenamento de dados
     */
    private static int typeOfStorage = 2;
    //1 - MEMORIA
    //* - ARQUIVO
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

    public static LivroDAO getLivroDAO() throws IOException, ClassNotFoundException {

        if (livroDAO == null){

            if(typeOfStorage == 1){
                livroDAO = new LivroDAOmap();
            }
            else{
                livroDAO = new ArquivoLivroDAO();
            }

        }
        return livroDAO;
    }
    /**
     * criar e retorna um objeto
     * @return objeto UsuarioDAOmap
     */
    public static UsuarioDAO getUsuarioDAO() throws IOException, ClassNotFoundException {

        if (usuarioDAO == null){

            if (typeOfStorage == 1){
                usuarioDAO = new UsuarioDAOmap();
            }
            else{
                usuarioDAO = new ArquivoUsuarioDAO();
            }


        }
        return usuarioDAO;

    }
    /**
     * criar e retorna um objeto
     * @return objeto OperadoresDAOmap
     */
    public static OperadoresDAO getOperadoresDAO() throws IOException, ClassNotFoundException {

        if (operadoresDAO == null){

            if (typeOfStorage == 1){
                operadoresDAO = new OperadoresDAOmap();
            }
            else{
                operadoresDAO = new ArquivoOperadoresDAO();
            }

        }

        return operadoresDAO;
    }
    /**
     * criar e retorna um objeto
     * @return objeto EmprestimoDAOmap
     */
    public static EmprestimoDAO getEmprestimoDAO() throws IOException, ClassNotFoundException {

        if (emprestimoDAO == null) {

            if(typeOfStorage == 1){
                emprestimoDAO = new EmprestimoDAOmap();
            }
            else{
                emprestimoDAO = new ArquivoEmprestimoDAO();
            }
        }
        return emprestimoDAO;
    }
}
