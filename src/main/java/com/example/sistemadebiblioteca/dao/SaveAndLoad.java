package main.dao;


import main.exceptions.dao.DAOExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.io.*;

import static main.dao.DAO.*;
import static main.dao.DAO.getEmprestimoDAO;

public class SaveAndLoad<T> {

    /**
     * nome do arquivo que deve ser criado
     */
    private String diretorio;

    /**
     * construtor da classe
     * @param diretorio nome do arquivo que deve ser criado
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    public SaveAndLoad(String diretorio) throws IOException, ClassNotFoundException {
        this.diretorio = diretorio;
    }

    /**
     * cria um arquivo vazio
     * @throws IOException excecoes de entrada e saida
     */
    public void criarArquivo() throws IOException {

        String diretorioAtual = (System.getProperty("user.dir") + "\\dados\\" + diretorio);

        ObjectOutputStream objectOutput = new ObjectOutputStream(
                new FileOutputStream(diretorioAtual));

        objectOutput.close();


    }

    /**
     * verifica se há um arquivo ja criado
     * @return true ou false
     */
    public boolean verArquivo(){

        String diretorioAtual = (System.getProperty("user.dir") + "\\dados\\" + diretorio);
        Path path = Paths.get(diretorioAtual);

        if(Files.exists((path))){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * salva os dados em um arquivo
     * @param typeOfDAO tipo de DAO que sera lidado
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @SuppressWarnings("unchecked")
    public void salvar(Integer typeOfDAO) throws IOException, ClassNotFoundException {

        String diretorioAtual = (System.getProperty("user.dir") + "\\dados\\" + diretorio);
        Map<Integer, T> dic = null;

        if (typeOfDAO == 1){
            dic = (Map<Integer, T>) getEmprestimoDAO().findMany();
        }
        else if(typeOfDAO == 2){
            dic = (Map<Integer, T>) getLivroDAO().findMany();
        }
        else if(typeOfDAO == 3){
            dic = (Map<Integer, T>) getUsuarioDAO().findMany();
        }
        else if(typeOfDAO == 4){
            dic = (Map<Integer, T>) getOperadoresDAO().findMany();
        }

        ObjectOutputStream objectOutput = new ObjectOutputStream(
                new FileOutputStream(diretorioAtual)
        );

        try{
            objectOutput.writeObject(dic);
        }
        catch (IOException e){
            throw e;
        }
        finally {
            objectOutput.close();
        }

    }

    /**
     * carrega os dados do arquivo para um map
     * @return map com os dados
     * @throws IOException excecoes de entrada e saida
     * @throws ClassNotFoundException excecoes de classes nao encontradas
     */
    @SuppressWarnings("unchecked")
    public Map<Integer,T> carregar() throws IOException, ClassNotFoundException {

        String diretorioAtual = (System.getProperty("user.dir") + "\\dados\\" + diretorio);

        ObjectInputStream objectInput = new ObjectInputStream(
                new FileInputStream(diretorioAtual)
        );

        try {
            Map<Integer, T> map = (Map<Integer, T>) objectInput.readObject();
            return map;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        } finally {
            objectInput.close();
        }

    }

    /**
     * busca o maior ID do map
     * @param map map com os dados
     * @return maior ID
     */
    public Integer maiorID(Map<Integer,T> map){

        Integer maiorValor = 0;

        for (Entry<Integer, T> x : map.entrySet()) {
            if (x.getKey() > maiorValor) {
                maiorValor = x.getKey();

            }
        }

        return maiorValor;
    }


}

