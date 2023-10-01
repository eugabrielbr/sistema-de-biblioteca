package main.exceptions.usecases;

public class BlibUseCaseExceptions extends Exception{

    private Integer ID;
    public static final String MULTADO = "Usuario Multado";
    public static final String RESERVADO = "Livro ja reservado";
    public static final String ATIVOS = "Usuario com 3 emprestimos ativos";
    public static final String INDISPONIVEL = "Livro indisponivel";
    public static final String BLOQUEADO = "Usuario Bloqueado";

    public static final String FAILDEVOLUCAO = "A devolucao nao foi bem-sucedida. Verifique se o livro informado esta correto ou se ha um emprestimo correspondente a devolucao cadastrado";

    public BlibUseCaseExceptions(String message, Integer ID){
        super (message + " - ID: " + ID);
        this.ID = ID;
    }



}
