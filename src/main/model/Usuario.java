package main.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Classe com atributos e metodos relacionados as usuario
 * @author Gabriel
 */
public class Usuario extends Pessoa {

    /**
     * endereco do usuario
     */
    private String endereco;
    /**
     * telefone do usuario
     */
    private String telefone;
    /**
     * situacao de bloqueio do usuario
     */
    private boolean bloqueio;
    /**
     * quantidade de emprestimos do usuario
     */
    private int qtdEmprestimos;
    /**
     * data limite de multa do usuario
     */
    private LocalDate dataDaMulta;
    /**
     * historico de emprestimos do usuario
     */
    private List<Emprestimo> historico;
    /**
     * historico de devolucao do usuario
     */
    private List<Emprestimo> livrosDevolvidos;


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
        this.livrosDevolvidos = new ArrayList<>();
        this.historico = new ArrayList<>();


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

    public void somarEmprestimos(){
        qtdEmprestimos++;
    }


    public List<Emprestimo> getHistorico() {
        return historico;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", bloqueio=" + bloqueio +
                ", qtdEmprestimos=" + qtdEmprestimos +
                ", dataDaMulta=" + dataDaMulta +
                ", ID=" + getID() +
                '}';
    }

    public void testPrint( Integer opcao){

        if (opcao == 1){
            for (Emprestimo x : historico){
                System.out.println(x);
            }
        }
        else if(opcao == 2){
            for (Emprestimo y : livrosDevolvidos){
                System.out.println(y);
            }
        }


    }
    public List<Emprestimo> getLivrosDevolvidos() {
        return livrosDevolvidos;
    }

    public void setLivrosDevolvidos( List<Emprestimo> livrosDevolvidos ) {
        this.livrosDevolvidos = livrosDevolvidos;
    }
}
