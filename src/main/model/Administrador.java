package main.model;


public class Administrador extends Pessoa {


    private String cargo;
    private String senha;


    public Administrador( String name, String cargo,String senha) {
        super(name);
        this.cargo = cargo;
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo( String cargo ) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha( String senha ) {
        this.senha = senha;
    }
}
