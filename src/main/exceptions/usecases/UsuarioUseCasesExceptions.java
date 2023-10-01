package main.exceptions.usecases;

public class UsuarioUseCasesExceptions extends Exception{

    private Integer ID;
    public static final String ERRO  = "Nao e possivel efetuar a renovacao";

    public UsuarioUseCasesExceptions(String message, Integer ID){
        super (message + " - ID: " + ID);
        this.ID = ID;
    }
}
