package main.model;

import java.time.LocalDate;
import java.util.*;
/**
 * Classe com atributos e metodos relacionados aos livros
 * @author Gabriel
 */
public class Livro {

    /**
     * titulo do livro
     */
    private String titulo;
    /**
     * autor do livro
     */
    private String autor;
    /**
     * editora do livro
     */
    private String editora;
    /**
     * ISBN do livro
     */
    private Integer ISBN;
    /**
     * categoria do livro
     */
    private String categoria;
    /**
     * disponibilidade do livro
     */
    private boolean disponibilidade;
    /**
     * localizacao do livro
     */
    private String localizacao;
    /**
     * ID do livro
     */
    private Integer ID;
    /**
     * fila com os usuarios que reservaram o livro
     */
    private Queue<Integer> filaReserva;
    /**
     * prazo para o usuario com prioridade fazer o emprestimo
     */
    private LocalDate dataReserva;
    /**
     * quantidade de emprestimos feitos
     */
    private Integer qtdEmprestimo;

    /**
     *
     * @param titulo titulo do livro
     * @param autor autor do livro
     * @param editora editora do livro
     * @param ISBN ISBN do livro
     * @param categoria categoria do livro
     * @param localizacao localizacao do livro
     */
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
        this.dataReserva = null;

    }

    public String getTitulo() {
        return titulo;
    }

    /**
     * seta mais um na quantidade de emprestimos
     */
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

    /**
     * adiciona id de usuario na reserva
     * @param ID id do usuario
     */
    public void adicionarReserva(Integer ID){

        filaReserva.offer(ID);
    }

    /**
     * remove o primeiro da fila
     */
    public void removerFila(){

        filaReserva.poll();

    }

    /**
     * pega o primeiro da fila e retorna
     * @return primeiro da fila
     */
    public Integer primeiroFila(){

        return filaReserva.peek();
    }
}
