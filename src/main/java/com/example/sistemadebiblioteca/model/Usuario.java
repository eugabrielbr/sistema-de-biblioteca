package com.example.sistemadebiblioteca.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Classe com atributos e metodos relacionados as usuario
 * @author Gabriel
 */
public class Usuario extends Pessoa implements Serializable {

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

    private String senha;
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

    /**
     * adiciona um objeto emprestimo no historico de emprestimos do usuario
     * @param emprestimo objeto emprestimo
     */
    public void registroHistoricoUser(Emprestimo emprestimo){

        historico.add(emprestimo);
    }

    /**
     * aciciona um objeto emprestimo no historico de devolucoes do usuario
     * @param emprestimo objeto emprestimo
     */
    public void registrosLivrosDevolvidos( Emprestimo emprestimo){

        livrosDevolvidos.add(emprestimo);
    }

    /**
     * construtor da classe Usuario
     * @param name nome do usuario
     * @param endereco endereco do usuario
     * @param telefone telefone do usuario
     */
    public Usuario(String name, String endereco, String telefone, String senha) {
        super(name);
        this.endereco = endereco;
        this.telefone = telefone;
        this.livrosDevolvidos = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.senha = senha;


    }

    /**
     * retorna a data da multa do usuario
     * @return data da multa do usuario
     */
    public LocalDate getDataDaMulta() {
        return dataDaMulta;
    }

    /**
     * seta a data da multa
     * @param dataDaMulta data da multa
     */
    public void setDataDaMulta( LocalDate dataDaMulta ) {
        this.dataDaMulta = dataDaMulta;
    }

    /**
     * retorna o endereco do usuario
     * @return endereco do usuario
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * seta o endereco do usuario
     * @param endereco endereco do usuario
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * retorna o telefone do usuario
     * @return telefone do usuario
     */
    public String getTelefone() {
        return telefone;
    }
    public String getSenha(){return senha;}
    public void setSenha(String senha){
        this.senha = senha;
    }

    /**
     * seta o telefone do usuario
     * @param telefone telefone do usuario
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * retorna a situacao de bloqueio do usuario
     * @return situacao de bloqueio do usuario
     */
    public boolean getBloqueio() {
        return bloqueio;
    }

    /**
     * seta a situacao de bloqueio do usuario
     * @param bloqueio situacao de bloqueio do usuario
     */
    public void setBloqueio(boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    /**
     * retorna a quantidade de emprestimos do usuario
     * @return quantidade de emprestimo do usuario
     */
    public int getQtdEmprestimos() {
        return qtdEmprestimos;
    }

    /**
     * seta a quantidade de emprestimos do usuario
     * @param qtdEmprestimos quantidade de emprestimo do usuario
     */
    public void setQtdEmprestimos(int qtdEmprestimos) {
        this.qtdEmprestimos = qtdEmprestimos;

    }

    /**
     * seta mais um na quatidade de emprestimos do usuario
     */
    public void somarEmprestimos(){
        qtdEmprestimos++;
    }

    /**
     * retorna o historico de emprestimo do usuario
     * @return lista (historico) de emprestimos do usuario
     */
    public List<Emprestimo> getHistorico() {
        return historico;
    }

    /**
     * retorna uma string formatada com as informacoes dos atributos do objeto Usuario
     * @return string formatada com as informacoes dos atributos do objeto Usuario
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nome= " + getName() +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", bloqueio=" + bloqueio +
                ", qtdEmprestimos=" + qtdEmprestimos +
                ", dataDaMulta=" + dataDaMulta +
                ", ID=" + getID() +
                '}';
    }

    /**
     * retorna o historico de livros devolvidos do usuario
     * @return lista (historico) de livros devolvidos
     */
    public List<Emprestimo> getLivrosDevolvidos() {
        return livrosDevolvidos;
    }

    /**
     * seta uma lista de livros devolvidos do usuario
     * @param livrosDevolvidos lista de livros devolvidos
     */
    public void setLivrosDevolvidos( List<Emprestimo> livrosDevolvidos ) {
        this.livrosDevolvidos = livrosDevolvidos;
    }
}
