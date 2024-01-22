package com.example.sistemadebiblioteca.dao.emprestimo;

import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

/**
 * DAO da classe Emprestimo
 * @author Gabriel
 */
public class EmprestimoDAOmap implements EmprestimoDAO{

    /**
     * contador para id
     */
    private int ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer, Emprestimo> emprestimoMap;

    public EmprestimoDAOmap(){

        this.ID = 0;
        this.emprestimoMap = new HashMap<>();


    }

    /**
     * registra emprestimos
     * @param emprestimo objeto emprestimo
     */
    @Override
    public void create( Emprestimo emprestimo ) {

        ID++;
        emprestimo.setIDemprestimo(ID);
        emprestimoMap.put(ID,emprestimo);

    }

    /**
     * deleta emprestimo
     * @param ID ID do emprestimo
     * @throws DAOExceptions exceções do DAO
     */
    @Override
    public void delete( int ID ) throws DAOExceptions {

        Emprestimo emprestimo = emprestimoMap.remove(ID);

        if (emprestimo == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }
    }

    /**
     * deleta todos os emprestimo e zera contador de id
     * @throws DAOExceptions exceções do DAO
     */
    @Override
    public void deleteMany() throws DAOExceptions {

        this.emprestimoMap = new HashMap<>();
        this.ID = 0;
        
    }

    /**
     * atualiza emprestimo por outro
     * @param emprestimo objeto emprestimo
     * @param ID ID do emprestimo que deve substituir
     * @throws DAOExceptions exceções do DAO
     */
    @Override
    public void update( Emprestimo emprestimo, Integer ID ) throws DAOExceptions {

        Emprestimo get = emprestimoMap.get(ID);

        if (get != null) {

            emprestimo.setIDemprestimo(ID);
            emprestimoMap.put(ID, emprestimo);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }
        
    }

    /**
     * retorna o map com os emprestimos
     * @return Map dos emprestimos
     */
    @Override
    public Map<Integer, Emprestimo> findMany() {

        return emprestimoMap;

    }

    /**
     * busca emprestimo por id e retorna
     * @param id id do emprestimo
     * @return emprestimo encontrado na busca
     * @throws DAOExceptions exceções do DAO
     */
    @Override
    public Emprestimo findById( int id ) throws DAOExceptions {

        Emprestimo busca = emprestimoMap.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,id);
        }
    }

    /**
     * retorna uma lista com emprestimos do usuario
     * @param IDuser ID do usuario
     * @return lista de emprestimos do usuario
     */
    @Override
    public List<Emprestimo> findByUser(Integer IDuser) {

        List<Emprestimo> listaEmprestimo = new ArrayList<>();

        for (Entry<Integer,Emprestimo> x : emprestimoMap.entrySet()){

            if (x.getValue().getUsuario().getID() == IDuser){
                listaEmprestimo.add(x.getValue());
            }

        }

        return listaEmprestimo;
    }

    /**
     * buscar e retorna emprestimo do usuario
     * @param IDlivro ID do livro
     * @param IDusuario ID do usuario
     * @return emprestimo do usuario
     * @throws DAOExceptions exceções do DAO
     */
    public Emprestimo findByIDlivroIDusuario( Integer IDlivro, Integer IDusuario ) throws DAOExceptions {

        Emprestimo emprestimo = null;

        for (Entry<Integer,Emprestimo> x : emprestimoMap.entrySet()){

            if (x.getValue().getUsuario().getID() == IDusuario && x.getValue().getLivro().getID() == IDlivro){

                emprestimo = x.getValue();
                return emprestimo;
            }

        }
        if (emprestimo == null){

            throw new DAOExceptions(DAOExceptions.NOT_FOUND,IDusuario);
        }
        return emprestimo;

    }


}
