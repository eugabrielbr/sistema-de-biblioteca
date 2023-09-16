package main.model;

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

    public Usuario(String name, String endereco, String telefone) {
        super(name);
        this.endereco = endereco;
        this.telefone = telefone;

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

    @Override
    public String toString() {
        return "Usuario{" +
                super.toString() +
                "endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", bloqueio=" + bloqueio +
                ", qtdEmprestimos=" + qtdEmprestimos +
                ", numRenovacoes=" + numRenovacoes +
                '}';
    }
}
