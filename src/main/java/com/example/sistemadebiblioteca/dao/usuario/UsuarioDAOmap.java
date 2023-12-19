package main.dao.usuario;


import main.exceptions.dao.DAOExceptions;

import main.model.Usuario;

import java.util.*;

/**
 * DAO da classe Usuario
 * @author Gabriel
 */
public class UsuarioDAOmap implements UsuarioDAO{

    /**
     * contador do ID
     */
    private Integer ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer, Usuario> usuarioMap;
    
    public UsuarioDAOmap(){

        this.ID = 0;
        this.usuarioMap = new HashMap<>();
    }

    /**
     * registra usuario
     * @param usuario objeto usuario
     */
    @Override
    public void create( Usuario usuario ){

        ID++;
        usuario.setID(ID);
        usuarioMap.put(ID,usuario);


    }

    /**
     * deleta usuarios
     * @param ID id usuario
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void delete(int ID) throws DAOExceptions {

        Usuario usuario = usuarioMap.remove(ID);

        if (usuario == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }

    }

    /**
     * deleta todos os dados e reseta o contador de id
     */
    @Override
    public void deleteMany(){

        this.usuarioMap = new HashMap<>();
        this.ID = 0;
        
    }

    /**
     * atualiza um usuario por outro
     * @param usuario objeto usuario
     * @param ID id do usuario que vai ser substituido
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void update(Usuario usuario, Integer ID) throws DAOExceptions {

        Usuario get = usuarioMap.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            usuario.setID(newId);
            usuarioMap.put(newId, usuario);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }
    }

    /**
     * retorna o map de usuarios
     * @return map de usuarios
     */
    @Override
    public Map<Integer,Usuario> findMany() {

        return usuarioMap;
    }

    /**
     * busca e retorna usuario pelo seu id
     * @param id id do usuario
     * @return resultado da busca de usuario
     * @throws DAOExceptions excecoes do dao
     */
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
