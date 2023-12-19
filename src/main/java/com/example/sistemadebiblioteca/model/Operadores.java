package main.model;

import java.io.Serializable;

/**
 * Classe com atributos e metodos relacionados aos operadores
 * @author Gabriel
 */
public class Operadores extends Pessoa implements Serializable {

    /**
     * cargo dos operadores
     */
    private Integer cargo; //1 adm   2 biblio
    /**
     * senha dos operadores
     */
    private String senha;

    /**
     * construtor da classe Operadores
     * @param name nome do operador
     * @param cargo cargo do operador
     * @param senha senha do operador
     */
    public Operadores( String name, Integer cargo,String senha ) {

        super(name);
        this.cargo = cargo;
        this.senha = senha;
    }

    /**
     * retorna o cargo do operador
     * @return cargo do operador
     */
    public Integer getCargo() {
        return cargo;
    }

    /**
     * seta o catgo do operador
     * @param cargo cargo do operador
     */
    public void setCargo( Integer cargo ) {
        this.cargo = cargo;
    }

    /**
     * retorna senha do operador
     * @return senha do operador
     */
    public String getSenha() {
        return senha;
    }

    /**
     * seta senha do operador
     * @param senha senha do operador
     */
    public void setSenha( String senha ) {
        this.senha = senha;
    }
}
