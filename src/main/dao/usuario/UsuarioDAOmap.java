package main.dao.usuario;


import main.exceptions.crud.DAOExceptions;

import main.model.Usuario;

import java.util.*;

public class UsuarioDAOmap implements UsuarioDAO{

    private Integer ID;
    public Map<Integer, Usuario> usuarioMap;
    
    public UsuarioDAOmap(){

        this.ID = 0;
        this.usuarioMap = new HashMap<>();
    }

    @Override
    public void create( Usuario usuario ){

        ID++;
        usuario.setID(ID);
        usuarioMap.put(ID,usuario);

    }

    @Override
    public void delete(int ID) throws DAOExceptions {

        Usuario usuario = usuarioMap.remove(ID);

        if (usuario == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }

    }

    @Override
    public void deleteMany(){

        this.usuarioMap = new HashMap<>();
        this.ID = 0;
        
    }

    @Override
    public void update(Usuario usuario, Integer ID) throws DAOExceptions {

        Usuario get = usuarioMap.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            usuarioMap.put(newId, usuario);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }
    }

    @Override
    public Map<Integer,Usuario> findMany() {

        return usuarioMap;
    }

    @Override
    public Usuario findById(int id) throws DAOExceptions {

        Usuario busca = usuarioMap.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,id);
        }
    }



}
