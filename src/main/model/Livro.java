package main.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Livro {

    private String titulo;
    private String autor;
    private String editora;
    private Integer ISBN;
    private String categoria;
    private boolean disponibilidade;
    private String localizacao;
    private Integer ID;
    private Queue<Integer> filaReserva;
    private LocalDate dataReserva;

    private Integer qtdEmprestimo;

    public Livro(String titulo, String autor, String editora, Integer ISBN, String categoria, String localizacao) {

        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.disponibilidade = true;
        this.localizacao = localizacao;
        this.filaReserva = new LinkedList<>();
        this.qtdEmprestimo = 0;
        this.setDataReserva(null);

    }

    public String getTitulo() {
        return titulo;
    }

    public void setarMaisUm(){

        qtdEmprestimo++;

    }
    public Integer getQtdEmprestimo() {
        return qtdEmprestimo;
    }

    public void setQtdEmprestimo( Integer qtdEmprestimo ) {
        this.qtdEmprestimo = qtdEmprestimo;
    }

    public void setTitulo( String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String  categoria) {
        this.categoria = categoria;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", ISBN=" + ISBN +
                ", categoria='" + categoria + '\'' +
                ", disponibilidade=" + disponibilidade +
                ", localizacao='" + localizacao + '\'' +
                ", ID=" + ID +
                ", dataReserva=" + dataReserva +
                ", qtdEmprestimo=" + qtdEmprestimo +
                '}';
    }




    public Queue<Integer> getFilaReserva() {
        return filaReserva;
    }

    public void setFilaReserva( Queue<Integer> filaReserva ) {
        this.filaReserva = filaReserva;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva( LocalDate dataReserva ) {
        this.dataReserva = dataReserva;
    }

    public void adicionarReserva(Integer ID){

        filaReserva.offer(ID);
    }

    public void removerFila(){

        filaReserva.poll();

    }

    public Integer primeiroFila(){

        return filaReserva.peek();
    }
}
