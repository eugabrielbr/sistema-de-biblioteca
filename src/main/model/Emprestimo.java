package main.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe com atributos e metodos relacionados aos emprestimos
 * @author Gabriel
 */
public class Emprestimo {

    /**
     * objeto do usuario
     */
    private Usuario usuario;
    /**
     * data do emprestimo
     */
    private LocalDate dataEmprestimo;
    /**
     * data da devolucao
     */
    private LocalDate dataDevolucao;
    /**
     * objeto livro
     */
    private Livro livro;
    /**
     * id do emprestimo
     */
    private int IDemprestimo;
    /**
     * se foi renovado ou nao
     */
    private boolean foiRenovado;

    /**
     * construtor da classe
     * @param usuario objeto usuario
     * @param dataEmprestimo data de emprestimo
     * @param livro objeto livro
     */
    public Emprestimo(Usuario usuario, LocalDate dataEmprestimo, Livro livro) {
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataEmprestimo.plusDays(10);
        this.livro = livro;
        this.foiRenovado = false;

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getIDemprestimo() {
        return IDemprestimo;
    }

    public void setIDemprestimo( int IDemprestimo ) {
        this.IDemprestimo = IDemprestimo;
    }

    public boolean getFoiRenovado() {
        return foiRenovado;
    }

    public void setFoiRenovado( boolean foiRenovado ) {
        this.foiRenovado = foiRenovado;
    }


    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", livro=" + livro +
                ", IDemprestimo=" + IDemprestimo +
                ", foiRenovado=" + foiRenovado +
                '}';
    }

}

