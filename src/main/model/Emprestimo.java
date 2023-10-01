package main.model;

import java.time.LocalDate;

public class Emprestimo {

    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Livro livro;
    private int IDemprestimo;

    private boolean foiRenovado;

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

