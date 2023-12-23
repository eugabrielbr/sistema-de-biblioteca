package main.java.com.example.sistemadebiblioteca.dao.usuario;

import main.java.com.example.sistemadebiblioteca.dao.SaveAndLoad;
import main.java.com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import main.java.com.example.sistemadebiblioteca.model.Usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArquivoUsuarioDAO implements UsuarioDAO{

    /**
     * nome do arquivo a ser criado
     */
    public String diretorioAtual = "usuario.bin";
    /**
     * contador do ID
     */
    private Integer ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer, Usuario> usuarioMap;
    /**
     * instancia da classe SaveAndLoad
     */
    private SaveAndLoad<Usuario> save = new SaveAndLoad<>(diretorioAtual);
    /**
     * construtor da classe
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    public ArquivoUsuarioDAO() throws IOException, ClassNotFoundException {

        if (!save.verArquivo()){
            save.criarArquivo();
        }

        if (save.carregar() == null){
            this.usuarioMap = new HashMap<>();
            this.ID = 0;
        }
        else{

            this.usuarioMap = save.carregar();
            this.ID = save.maiorID(usuarioMap);

        }
    }
    /**
     * registra usuario
     * @param usuario objeto usuario
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void create( Usuario usuario ) throws IOException, ClassNotFoundException {
        ID++;
        usuario.setID(ID);
        usuarioMap.put(ID,usuario);
        save.salvar(3);
    }
    /**
     * deleta usuarios
     * @param ID id usuario
     * @throws DAOExceptions excecoes do dao
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void delete( int ID ) throws DAOExceptions, IOException, ClassNotFoundException {

        Usuario usuario = usuarioMap.remove(ID);

        if (usuario == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }
        save.salvar(3);
    }
    /**
     * deleta todos os dados e reseta o contador de id
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException {
        this.usuarioMap = new HashMap<>();
        this.ID = 0;
        save.salvar(3);
    }
    /**
     * atualiza um usuario por outro
     * @param usuario objeto usuario
     * @param ID id do usuario que vai ser substituido
     * @throws DAOExceptions excecoes do dao
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void update( Usuario usuario, Integer ID ) throws DAOExceptions, IOException, ClassNotFoundException {
        Usuario get = usuarioMap.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            usuario.setID(newId);
            usuarioMap.put(newId, usuario);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }
        save.salvar(3);
    }
    /**
     * retorna o map de usuarios
     * @return map de usuarios
     */
    @Override
    public Map<Integer, Usuario> findMany() {
        return usuarioMap;
    }
    /**
     * busca e retorna usuario pelo seu id
     * @param id id do usuario
     * @return resultado da busca de usuario
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public Usuario findById( int id ) throws DAOExceptions {
        Usuario busca = usuarioMap.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,id);
        }
    }
}
