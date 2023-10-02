package main.model;

import java.util.Objects;

/**
 * Classe com atributos e metodos relacionados aos operadores
 * @author Gabriel
 */
public class Operadores extends Pessoa {

    /**
     * cargo dos operadores
     */
    private Integer cargo; //1 adm   2 biblio
    /**
     * senha dos operadores
     */
    private String senha;

    /**
     * construtor da classe
     * @param name nome do operador
     * @param cargo cargo do operador
     * @param senha senha do operador
     */
    public Operadores( String name, Integer cargo,String senha ) {

        super(name);
        this.cargo = cargo;
        this.senha = senha;
    }


    public Integer getCargo() {
        return cargo;
    }

    public void setCargo( Integer cargo ) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha( String senha ) {
        this.senha = senha;
    }
}
