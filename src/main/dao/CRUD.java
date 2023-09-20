package main.dao;

import main.exceptions.crud.CrudExceptions;


import java.util.Map;

public interface CRUD<T> {

    public void create(T obj);

    /**
     * Deleta um objeto
     *
     * @param
     * @return
     */
    public void delete(int ID) throws CrudExceptions;

    /**
     * Detela todos os dados
     */
    public void deleteMany() throws CrudExceptions;

    /**
     * Atualiza um objeto
     *
     * @param obj
     * @return
     */
    public void update(T obj, Integer number) throws CrudExceptions;

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
    public T findById(int id) throws CrudExceptions;


}
