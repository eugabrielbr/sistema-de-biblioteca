package main.exceptions.usecases;
/**
 * excecoes personalizadas para a Classe UsuarioUseCases
 * @author Gabriel
 */
public class UsuarioUseCasesExceptions extends Exception{

    /**
     * ID do objeto correspondente a excecao
     */
    private Integer ID;
    /**
     * mensagem para excecao erro
     */
    public static final String ERRO  = "Nao e possivel efetuar a renovacao";
    /**
     * mensagem para excecao erro_reserva
     */
    public static final String ERRO_RESERVA  = "Nao e possivel efetuar a reserva";

    /**
     * construtor da excecao
     * @param message mensagem da excecao
     * @param ID id do objeto envolvido com a excecao
     */
    public UsuarioUseCasesExceptions(String message, Integer ID){
        super (message + " - ID: " + ID);
        this.ID = ID;
    }
}
