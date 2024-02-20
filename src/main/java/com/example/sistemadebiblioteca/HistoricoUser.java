package com.example.sistemadebiblioteca;

public class HistoricoUser {

    private String titulo;
    private String dataEmprestimo;
    private String dataDevolucao;
    private String id;

    public HistoricoUser(String titulo, String dataEmprestimo, String dataDevolucao, String id){

        this.titulo = titulo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo( String titulo ) {
        this.titulo = titulo;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo( String dataEmprestimo ) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao( String dataDevolucao ) {
        this.dataDevolucao = dataDevolucao;
    }
}
