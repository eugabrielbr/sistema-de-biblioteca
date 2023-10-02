package main.model;
/**
 * Classe com atributos e metodos relacionados as pessoas
 * @author Gabriel
 */
public class Pessoa {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Pessoa{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }


}
