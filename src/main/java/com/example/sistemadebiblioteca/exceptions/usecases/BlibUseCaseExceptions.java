package com.example.sistemadebiblioteca.exceptions.usecases;

/**
 * excecoes personalizadas para a Classe BibliUseCases
 * @author Gabriel
 */
public class BlibUseCaseExceptions extends Exception{

    /**
     * ID do objeto correspondente a excecao
     */
    private Integer ID;
    /**
     * mensagem para excecao multado
     */
    public static final String MULTADO = "Usuario Multado";
    /**
     * mensagem para excecao reservado
     */
    public static final String RESERVADO = "Livro ja reservado";
    /**
     * mensagem para excecao ativos
     */
    public static final String ATIVOS = "Usuario com 3 emprestimos ativos";
    /**
     * mensagem para excecao indisponivel
     */
    public static final String INDISPONIVEL = "Livro indisponivel";
    /**
     * mensagem para excecao bloqueado
     */
    public static final String BLOQUEADO = "Usuario Bloqueado";
    /**
     * mensagem para excecao faildevolucao
     */

    public static final String FAILDEVOLUCAO = "A devolucao nao foi bem-sucedida. Verifique se o livro informado esta correto ou se ha um emprestimo correspondente a devolucao cadastrado";

    /**
     * construtor da excecao
     * @param message mensagem da excecao
     * @param ID id do objeto envolvido com a excecao
     */
    public BlibUseCaseExceptions(String message, Integer ID){
        super (message + " - ID: " + ID);
        this.ID = ID;
    }



}
