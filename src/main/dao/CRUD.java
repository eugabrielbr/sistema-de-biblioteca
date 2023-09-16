package main.dao;

import main.exceptions.livro.LivroExceptions;
import main.exceptions.usuario.UsuarioExceptions;

import java.util.Map;

public interface CRUD<T> {

    public void create(T obj);

    /**
     * Deleta um objeto
     *
     * @param
     * @return
     */
    public void delete(int ID) throws LivroExceptions, UsuarioExceptions;

    /**
     * Detela todos os dados
     */
    public void deleteMany() throws LivroExceptions;

    /**
     * Atualiza um objeto
     *
     * @param obj
     * @return
     */
    public void update(T obj, Integer number) throws LivroExceptions, UsuarioExceptions;

    /**
     * Ler toda a lista de dados
     *
     * @return
     */
    public Map<Integer, T> findMany();

    /**
     * Encontra um objeto específico pelo id
     *
     * @param id
     * @return
     */
    public T findById(int id) throws LivroExceptions, UsuarioExceptions;


}
