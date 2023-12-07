package main.dao.emprestimo;

import main.dao.SaveAndLoad;
import main.exceptions.dao.DAOExceptions;
import main.model.Emprestimo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ArquivoEmprestimoDAO implements EmprestimoDAO{

    /**
     * nome do arquivo a ser criado
     */
    public String diretorioAtual = "emprestimo.bin";

    /**
     * contador para id
     */
    private int ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer, Emprestimo> emprestimoMap;
    /**
     * instancia da classe SaveAndLoad
     */
    private SaveAndLoad<Emprestimo> save = new SaveAndLoad<>(diretorioAtual);

    /**
     * construtor da classe
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    public ArquivoEmprestimoDAO () throws IOException, ClassNotFoundException {

        if (!save.verArquivo()){
            save.criarArquivo();
        }

        if (save.carregar() == null){
            this.emprestimoMap = new HashMap<>();
            this.ID = 0;
        }
        else{

            this.emprestimoMap = save.carregar();
            this.ID = save.maiorID(emprestimoMap);

        }

    }
    /**
     * registra emprestimos
     * @param emprestimo objeto emprestimo
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void create( Emprestimo emprestimo ) throws IOException, ClassNotFoundException {

        ID++;
        emprestimo.setIDemprestimo(ID);
        emprestimoMap.put(ID,emprestimo);
        save.salvar(1);

    }
    /**
     * deleta emprestimo
     * @param ID ID do emprestimo
     * @throws DAOExceptions exceções do DAO
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void delete( int ID ) throws DAOExceptions, IOException, ClassNotFoundException {

        Emprestimo emprestimo = emprestimoMap.remove(ID);

        if (emprestimo == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }
        save.salvar(1);
    }
    /**
     * deleta todos os emprestimo e zera contador de id
     * @throws DAOExceptions exceções do DAO
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException {
        this.emprestimoMap = new HashMap<>();
        this.ID = 0;
        save.salvar(1);
    }
    /**
     * atualiza emprestimo por outro
     * @param emprestimo objeto emprestimo
     * @param ID ID do emprestimo que deve substituir
     * @throws DAOExceptions exceções do DAO
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void update( Emprestimo emprestimo, Integer ID ) throws DAOExceptions, IOException, ClassNotFoundException {

        Emprestimo get = emprestimoMap.get(ID);

        if (get != null) {

            emprestimo.setIDemprestimo(ID);
            emprestimoMap.put(ID, emprestimo);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }

        save.salvar(1);
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
    public List<Emprestimo> findByUser( Integer IDuser ) {

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
    @Override
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
