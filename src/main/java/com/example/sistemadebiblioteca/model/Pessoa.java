package main.java.com.example.sistemadebiblioteca.model;

import java.io.Serializable;

/**
 * Classe com atributos e metodos relacionados as pessoas
 * @author Gabriel
 */
public class Pessoa implements Serializable {
    /**
     * nome da pessoa
     */
    private String name;
    /**
     * ID da pessoa
     */
    private Integer ID;

    /**
     * construtor da classe
     * @param name nome da pessoa
     */
    public Pessoa(String name) {
        this.name = name;

    }

    /**
     * retorna o nome da pessoa
     * @return nome da pessoa
     */
    public String getName() {
        return name;
    }

    /**
     * seta o nome da pessoa
     * @param name nome da pessoa
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * retorna o ID da pessoa
     * @return ID da pessoa
     */
    public Integer getID() {
        return ID;
    }

    /**
     * seta o ID da pessoa
     * @param ID ID da pessoa
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * retorna uma string formatada com as informacoes dos atributos do objeto Pessoa
     * @return string formatada com as informacoes dos atributos do objeto Pessoa
     */
    @Override
    public String toString() {
        return "Pessoa{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }


}
