package main.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa {

    private String endereco;
    private String telefone;
    private boolean bloqueio;
    private int qtdEmprestimos;
    private int numRenovacoes;

    private LocalDate dataDaMulta;

    List<Emprestimo> historico = new ArrayList<>();
    List<Emprestimo> livrosDevolvidos = new ArrayList<>();

    public void reservarLivros(Integer id){
        //
    }

    public void renovarEmprestimos(){
        //
    }

    public void registroHistoricoUser(Emprestimo emprestimo){

        historico.add(emprestimo);
    }

    public void registrosLivrosDevolvidos( Emprestimo emprestimo){

        livrosDevolvidos.add(emprestimo);
    }

    public Usuario(String name, String endereco, String telefone) {
        super(name);
        this.endereco = endereco;
        this.telefone = telefone;

    }

    public LocalDate getDataDaMulta() {
        return dataDaMulta;
    }

    public void setDataDaMulta( LocalDate dataDaMulta ) {
        this.dataDaMulta = dataDaMulta;
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

    public boolean getBloqueio() {
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

    public List<Emprestimo> getHistorico() {
        return historico;
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

    public List<Emprestimo> getLivrosDevolvidos() {
        return livrosDevolvidos;
    }

    public void setLivrosDevolvidos( List<Emprestimo> livrosDevolvidos ) {
        this.livrosDevolvidos = livrosDevolvidos;
    }
}
