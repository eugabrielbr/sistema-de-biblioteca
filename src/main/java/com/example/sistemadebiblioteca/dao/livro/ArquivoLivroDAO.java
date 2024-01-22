package com.example.sistemadebiblioteca.dao.livro;

import com.example.sistemadebiblioteca.dao.SaveAndLoad;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Livro;

import java.io.IOException;
import java.util.*;

public class ArquivoLivroDAO implements LivroDAO{

    /**
     * nome do arquivo a ser criado
     */
    public String diretorioAtual = "livro.bin";

    /**
     * contador para ID
     */
    private Integer ID;
    /**
     * map que vai guardar os dados
     */
    private Map<Integer,Livro> acervo;
    /**
     * instancia da classe SaveAndLoad
     */
    private SaveAndLoad<Livro> save = new SaveAndLoad<>(diretorioAtual);
    /**
     * construtor da classe
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    public ArquivoLivroDAO () throws IOException, ClassNotFoundException {

        if (!save.verArquivo()){
            save.criarArquivo();
        }

        if (save.carregar() == null){
            this.acervo = new HashMap<>();
            this.ID = 0;
        }
        else{

            this.acervo = save.carregar();
            this.ID = save.maiorID(acervo);

        }

    }
    /**
     * registra emprestimos
     * @param livro objeto livro
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void create( Livro livro) throws IOException, ClassNotFoundException {

        ID++;
        livro.setID(ID);
        acervo.put(ID,livro);
        save.salvar(2);
    }
    /**
     * deleta livro
     * @param ID ID do livro
     * @throws DAOExceptions exceções do DAO
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void delete( int ID ) throws DAOExceptions, IOException, ClassNotFoundException {

        Livro livro = acervo.remove(ID);

        if (livro == null){
            throw new DAOExceptions(DAOExceptions.DELETE,ID);
        }
        save.salvar(2);
    }
    /**
     * deleta todos os emprestimo e zera contador de id
     * @throws DAOExceptions exceções do DAO
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException {
        this.acervo = new HashMap<>();
        this.ID = 0;
        save.salvar(2);
    }
    /**
     * atualiza livro por outro
     * @param livro objeto livro
     * @param ID ID do livro que deve substituir
     * @throws DAOExceptions exceções do DAO
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @Override
    public void update( Livro livro, Integer ID ) throws DAOExceptions, IOException, ClassNotFoundException {

        Livro get = acervo.get(ID);

        if (get != null) {

            Integer newId = get.getID();
            livro.setID(newId);
            acervo.put(newId, livro);

        }
        else{
            throw new DAOExceptions(DAOExceptions.UPDATE,ID);
        }

        save.salvar(2);
    }
    /**
     * retorna o map com os livros
     * @return Map dos livros
     */
    @Override
    public Map<Integer, Livro> findMany() {
        return acervo;
    }
    /**
     * busca livro por id e retorna
     * @param id id do livro
     * @return livro encontrado na busca
     * @throws DAOExceptions exceções do DAO
     */
    @Override
    public Livro findById( int id ) throws DAOExceptions {

        Livro busca = acervo.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,id);
        }

    }

    /**
     * busca livros e retorna lista de livros de acordo com o autor
     * @param autor autor do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public List<Livro> findByAutor( String autor ) throws DAOExceptions {
        List <Livro> lista = new ArrayList<>();

        for (Map.Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getAutor(), autor)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }
    /**
     * busca livros e retorna lista de livros de acordo com o titulo
     * @param titulo titulo do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public List<Livro> findByTitulo( String titulo ) throws DAOExceptions {
        List <Livro> lista = new ArrayList<>();

        for (Map.Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getTitulo(), titulo)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }
    /**
     * busca livros e retorna lista de livros de acordo com a categoria
     * @param categoria categoria do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public List<Livro> findByCategoria( String categoria ) throws DAOExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Map.Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getCategoria(), categoria)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }
    /**
     * busca livros e retorna lista de livros de acordo com o ISBN
     * @param ISBN ISBN do livro
     * @return lista de livros
     * @throws DAOExceptions excecoes do dao
     */
    @Override
    public List<Livro> findByISBN( Integer ISBN ) throws DAOExceptions {

        List <Livro> lista = new ArrayList<>();

        for (Map.Entry<Integer,Livro> x : acervo.entrySet()){

            if (Objects.equals(x.getValue().getISBN(), ISBN)){
                lista.add(x.getValue());
            }
        }

        if (lista.isEmpty()){
            throw new DAOExceptions(DAOExceptions.NOT_FOUND,null);
        }
        else{
            return lista;
        }
    }
}
