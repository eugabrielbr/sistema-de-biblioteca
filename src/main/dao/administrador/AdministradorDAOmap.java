package main.dao.administrador;

import main.exceptions.livro.LivroExceptions;
import main.exceptions.usuario.UsuarioExceptions;
import main.model.Administrador;

import java.util.Map;

public class AdministradorDAOmap implements AdministradorDAO{ //CORRIGIR EXCECOES
    @Override
    public void create( Administrador obj ) {

    }

    @Override
    public void delete( int ID ) throws LivroExceptions, UsuarioExceptions {

    }

    @Override
    public void deleteMany() throws LivroExceptions {

    }

    @Override
    public void update( Administrador obj, Integer number ) throws LivroExceptions, UsuarioExceptions {

    }

    @Override
    public Map<Integer, Administrador> findMany() {
        return null;
    }

    @Override
    public Administrador findById( int id ) throws LivroExceptions, UsuarioExceptions {
        return null;
    }
}
