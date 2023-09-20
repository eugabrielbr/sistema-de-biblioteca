package main.dao.administrador;

import main.exceptions.crud.CrudExceptions;
import main.exceptions.crud.CrudExceptions;
import main.model.Administrador;

import java.util.Map;

public class AdministradorDAOmap implements AdministradorDAO{ //CORRIGIR EXCECOES
    @Override
    public void create( Administrador obj ) {

    }

    @Override
    public void delete( int ID ) throws CrudExceptions, CrudExceptions {

    }

    @Override
    public void deleteMany() throws CrudExceptions {

    }

    @Override
    public void update( Administrador obj, Integer number ) throws CrudExceptions, CrudExceptions {

    }

    @Override
    public Map<Integer, Administrador> findMany() {
        return null;
    }

    @Override
    public Administrador findById( int id ) throws CrudExceptions, CrudExceptions {
        return null;
    }
}
