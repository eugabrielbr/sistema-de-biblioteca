package com.example.sistemadebiblioteca.dao.operadores;

import com.example.sistemadebiblioteca.dao.SaveAndLoad;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Operadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArquivoOperadoresDAO implements OperadoresDAO{

    /**
     * nome do arquivo a ser criado
     */
    public String diretorioAtual = "operadores.bin";

    /**
     * contador para id
     */
    private int ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer, Operadores> operadoresMap;
    /**
     * instancia da classe SaveAndLoad
     */
    private SaveAndLoad<Operadores> save = new SaveAndLoad<>(diretorioAtual);
    /**
     * construtor da classe
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    public ArquivoOperadoresDAO() throws IOException, ClassNotFoundException {

        if (!save.verArquivo()){
            save.criarArquivo();
        }

        if (save.carregar() == null){
            this.operadoresMap = new HashMap<>();
            this.ID = 0;
        }
        else{

            this.operadoresMap = save.carregar();
            this.ID = save.maiorID(operadoresMap);

        }
    }

    /**
     * registra operadores
     * @param operadores objeto operadores
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public Integer create( Operadores operadores ) throws IOException, ClassNotFoundException {
        ID++;
        operadores.setID(ID);
        operadoresMap.put(ID,operadores);
        save.salvar(4);
        return ID;
    }
    /**
     * deleta operadores
     * @param ID id do operador
     * @throws DAOExceptions excecoes do dao
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void delete( int ID ) throws DAOExceptions, IOException, ClassNotFoundException {
        Operadores operadores = operadoresMap.remove(ID);

        if (operadores == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }
        save.salvar(4);
    }
    /**
     * deleta os dados e zera o contador do id
     * @throws DAOExceptions excecoes do dao
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException {
        this.operadoresMap = new HashMap<>();
        this.ID = 0;
        save.salvar(4);
    }
    /**
     * atualiza um operador por outro
     * @param operadores objeto operadores
     * @param ID id do operador que sera substituido
     * @throws DAOExceptions execoes do dao
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void update( Operadores operadores, Integer ID ) throws DAOExceptions, IOException, ClassNotFoundException {

        Operadores get = operadoresMap.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            operadores.setID(newId);
            operadoresMap.put(newId, operadores);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }
        save.salvar(4);
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
     * @throws DAOExceptions
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

        for(Map.Entry<Integer,Operadores> x: operadoresMap.entrySet()){

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

        for(Map.Entry<Integer,Operadores> x: operadoresMap.entrySet()){

            if (x.getValue().getCargo() == 2){
                lista.add(x.getValue());
            }
        }

        return lista;
    }
}
