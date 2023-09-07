package model;

public class Usuario extends Pessoa {

    private String endereco;
    private String telefone;
    private boolean bloqueio;
    private int qtdEmprestimos;
    private int numRenovacoes;



    public void reservarLivros(Integer id){
        //
    }

    public void renovarEmprestimos(){
        //
    }

    public Usuario(String name, int ID, String endereco, String telefone, boolean bloqueio, int qtdEmprestimos, int numRenovacoes) {
        super(name, ID);
        this.endereco = endereco;
        this.telefone = telefone;
        this.bloqueio = bloqueio;
        this.qtdEmprestimos = qtdEmprestimos;
        this.numRenovacoes = numRenovacoes;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    public int getQtdEmprestimos() {
        return qtdEmprestimos;
    }

    public void setQtdEmprestimos(int qtdEmprestimos) {
        this.qtdEmprestimos = qtdEmprestimos;
    }

    public int getNumRenovacoes() {
        return numRenovacoes;
    }

    public void setNumRenovacoes(int numRenovacoes) {
        this.numRenovacoes = numRenovacoes;
    }
}
