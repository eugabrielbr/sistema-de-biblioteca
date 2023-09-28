package main.dao.administrador;

import main.exceptions.crud.DAOExceptions;
import main.model.Administrador;

import java.util.Map;

public class AdministradorDAOmap implements AdministradorDAO{ //CORRIGIR EXCECOES
    @Override
    public void create( Administrador obj ) {

    }

    @Override
    public void delete( int ID ) throws  DAOExceptions {

    }

    @Override
    public void deleteMany() throws  DAOExceptions {

    }

    @Override
    public void update( Administrador obj, Integer number ) throws  DAOExceptions {

    }

    @Override
    public Map<Integer, Administrador> findMany() {
        return null;
    }

    @Override
    public Administrador findById( int id ) throws DAOExceptions {
        return null;
    }
}
