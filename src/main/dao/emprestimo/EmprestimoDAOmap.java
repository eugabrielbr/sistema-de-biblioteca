package main.dao.emprestimo;

import main.exceptions.livro.LivroExceptions;
import main.exceptions.usuario.UsuarioExceptions;
import main.model.Emprestimo;

import java.util.Map;

public class EmprestimoDAOmap implements EmprestimoDAO{ //CORRIGIR EXCECOES
    @Override
    public void create( Emprestimo obj ) {

    }

    @Override
    public void delete( int ID ) throws LivroExceptions, UsuarioExceptions {

    }

    @Override
    public void deleteMany() throws LivroExceptions {

    }

    @Override
    public void update( Emprestimo obj, Integer number ) throws LivroExceptions, UsuarioExceptions {

    }

    @Override
    public Map<Integer, Emprestimo> findMany() {
        return null;
    }

    @Override
    public Emprestimo findById( int id ) throws LivroExceptions, UsuarioExceptions {
        return null;
    }
}
