package com.example.sistemadebiblioteca.dao.operadores;

import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Operadores;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * DAO da classe Operadores
 * @author Gabriel
 */
public class OperadoresDAOmap implements OperadoresDAO{

    /**
     * contador do id
     */
    private int ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer, Operadores> operadoresMap;
   

    public OperadoresDAOmap() {
        this.ID = 0;
        this.operadoresMap = new HashMap<>();
       
    }

    /**
     * registra operadores
     * @param operadores objeto operadores
     */
    @Override
    public void create( Operadores operadores) {
        
        ID++;
        operadores.setID(ID);
        operadoresMap.put(ID,operadores);
    }

    /**
     * deleta operadores
     * @param ID id do operador
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void delete( int ID ) throws DAOExceptions {
        
        Operadores operadores = operadoresMap.remove(ID);

        if (operadores == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }
    }

    /**
     * deleta os dados e zera o contador do id
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public void deleteMany() throws DAOExceptions {
        
        this.operadoresMap = new HashMap<>();
        this.ID = 0;
    }

    /**
     * atualiza um operador por outro
     * @param operadores objeto operadores
     * @param ID id do operador que sera substituido
     * @throws DAOExceptions excecoes do DAO
     */
    @Override
    public void update( Operadores operadores , Integer ID ) throws DAOExceptions {

        Operadores get = operadoresMap.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            operadores.setID(newId);
            operadoresMap.put(newId, operadores );

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }

        
    }

    /**
     * retorna o map de operadores
     * @return map de operadores
     */
    @Override
    public Map<Integer, Operadores> findMany() {
        return operadoresMap;
    }

    /**
     * busca e retorna o operador encontrado
     * @param id id do operador
     * @return operador buscado
     * @throws DAOExceptions excecoes do DAO
     */
    @Override
    public Operadores findById( int id ) throws DAOExceptions {
        
        Operadores busca = operadoresMap.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,id);
        }
    }

    /**
     * busca e retorna lista de administradores
     * @return lista de administradores
     */
    @Override
    public List<Operadores> getAdministradores() {

        List<Operadores> lista = new ArrayList<>();

        for(Entry<Integer,Operadores> x: operadoresMap.entrySet()){

            if (x.getValue().getCargo() == 1){
                lista.add(x.getValue());
            }
        }

        return lista;
    }

    /**
     * busca e retorna lista de bibliotecarios
     * @return lista de bibliotecarios
     */
    @Override
    public List<Operadores> getBibliotecarios() {

        List<Operadores> lista = new ArrayList<>();

        for(Entry<Integer,Operadores> x: operadoresMap.entrySet()){

            if (x.getValue().getCargo() == 2){
                lista.add(x.getValue());
            }
        }

        return lista;
    }
}
