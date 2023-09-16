package main.dao.usuario;


import main.exceptions.usuario.UsuarioExceptions;

import main.model.Usuario;

import java.util.*;

public class UsuarioDAOmap implements UsuarioDAO{

    int ID = 0;
    public Map<Integer, Usuario> usuario_map = new HashMap<>();
    
    @Override
    public void create( Usuario usuario ){

        ID++;
        usuario.setID(ID);
        usuario_map.put(ID,usuario);

    }

    @Override
    public void delete(int ID) throws UsuarioExceptions {

        Usuario usuario = usuario_map.remove(ID);

        if (usuario == null){
            throw new UsuarioExceptions(UsuarioExceptions.DELETE,ID);
        }

    }

    @Override
    public void deleteMany(){

        this.usuario_map = new HashMap<>();
        this.ID = 0;
        
    }

    @Override
    public void update(Usuario usuario, Integer ID) throws UsuarioExceptions {

        Usuario get = usuario_map.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            usuario_map.put(newId, usuario);

        }
        else{
            throw new UsuarioExceptions(UsuarioExceptions.UPDATE,ID);
        }
    }

    @Override
    public Map<Integer,Usuario> findMany() {

        return usuario_map;
    }

    @Override
    public Usuario findById(int id) throws UsuarioExceptions {

        Usuario busca = usuario_map.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new UsuarioExceptions(UsuarioExceptions.NOT_FOUND,id);
        }
    }



}
