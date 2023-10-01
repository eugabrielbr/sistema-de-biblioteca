package main.exceptions.dao;

public class DAOExceptions extends Exception {

    private Integer ID;
    public static final String DELETE = "Nao foi possivel deletar";
    public static final String UPDATE = "Nao foi possivel atualizar";
    public static final String NOT_FOUND = "Nao foi possivel encontrar o(s) objeto(s)";


    public DAOExceptions( String message, Integer ID) {
        super(message + " - ID: " + ID);
        this.ID = ID;
    }
}
