package main.java.com.example.sistemadebiblioteca.exceptions.dao;

/**
 * excecoes personalizadas do DAO
 * @author Gabriel
 */
public class DAOExceptions extends Exception {

    /**
     * id do objeto correspondente a excecao
     */
    private Integer ID;
    /**
     * mensagem para excecao delete
     */
    public static final String DELETE = "Nao foi possivel deletar";
    /**
     * UPDATE mensagem para excecao update
     */
    public static final String UPDATE = "Nao foi possivel atualizar";
    /**
     * mensagem para excecao NOT_FOUND
     */
    public static final String NOT_FOUND = "Nao foi possivel encontrar o(s) objeto(s)";

    /**
     * construtor da excecao
     * @param message mensagem da excecao
     * @param ID id do objeto envolvido com a excecao
     */
    public DAOExceptions( String message, Integer ID) {
        super(message + " - ID: " + ID);
        this.ID = ID;
    }
}
