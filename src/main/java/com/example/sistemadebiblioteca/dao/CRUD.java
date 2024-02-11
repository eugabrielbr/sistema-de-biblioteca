package com.example.sistemadebiblioteca.dao;

import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;


import java.io.IOException;
import java.util.Map;

/**
 * interface do crud
 * @param <T> objeto generico
 */

public interface CRUD<T> {

    public Integer create(T obj) throws IOException, ClassNotFoundException;

    /**
     * Deleta um objeto
     *
     * @param
     * @return
     */
    public void delete(int ID) throws DAOExceptions, IOException, ClassNotFoundException;

    /**
     * Detela todos os dados
     */
    public void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException;

    /**
     * Atualiza um objeto
     *
     * @param obj
     * @return
     */
    public void update(T obj, Integer number) throws DAOExceptions, IOException, ClassNotFoundException;

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
    public T findById(int id) throws DAOExceptions;


}
