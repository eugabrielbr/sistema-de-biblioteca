package main.model;

public class Bibliotecario extends Pessoa {

    private String cargo;
    private String senha;
    public Bibliotecario(String name) {
        super(name);
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

