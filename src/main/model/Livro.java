package main.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
/**
 * Classe com atributos e metodos relacionados aos livros
 * @author Gabriel
 */
public class Livro implements Serializable {

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
     * construtor da classe Livro
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

    /**
     * retorna o titulo do livro
     * @return titulo do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * seta mais um na quantidade de emprestimos
     */
    public void setarMaisUm(){

        qtdEmprestimo++;

    }

    /**
     * retorna a quantidade de emprestimos feitos a esse livro
     * @return quantidade de emprestimos
     */
    public Integer getQtdEmprestimo() {
        return qtdEmprestimo;
    }

    /**
     * seta a quantidade de emprestimos
     * @param qtdEmprestimo quantidade de emprestimos
     */
    public void setQtdEmprestimo( Integer qtdEmprestimo ) {
        this.qtdEmprestimo = qtdEmprestimo;
    }

    /**
     * seta o titulo do livro
     * @param titulo titulo do livro
     */
    public void setTitulo( String titulo) {
        this.titulo = titulo;
    }

    /**
     * retorna o autor do livro
     * @return autor do livro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * seta o nome do autor
     * @param autor nome do autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * retorna o nome da editora
     * @return nome da editora
     */
    public String getEditora() {
        return editora;
    }

    /**
     * seta o nome da editora
     * @param editora nome da editora
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * retorna ISBN do livro
     * @return ISBN do livro
     */
    public Integer getISBN() {
        return ISBN;
    }

    /**
     * seta ISBN do livro
     * @param ISBN ISBN do livro
     */
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * retorna ID do livro
     * @return ID do livro
     */
    public Integer getID() {
        return ID;
    }

    /**
     * seta ID do livro
     * @param ID ID do livro
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * retorna a categoria do livro
     * @return categoria do livro
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * seta a categoria do livro
     * @param categoria categoria do livro
     */
    public void setCategoria(String  categoria) {
        this.categoria = categoria;
    }

    /**
     * retorna um booleano em relacao a disponibilidade do livro
     * @return booleano em relacao a disponibilidade do livro
     */
    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    /**
     * seta um booleano em relacao a disponibilidade do livro
     * @param disponibilidade disponibilidade do livro
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    /**
     * retorna a localizacao do livro
     * @return localizacao do livro
     */
    public String getLocalizacao() {
        return localizacao;
    }

    /**
     * seta a localizacao do livro
     * @param localizacao localizacao do livro
     */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    /**
     * retorna uma string formatada com as informacoes dos atributos do objeto Livro
     * @return string formatada com as informacoes dos atributos do objeto Livro
     */
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

    /**
     * retorna a fila de reserva do livro
     * @return lista que é a fila de reserva do livro
     */
    public Queue<Integer> getFilaReserva() {
        return filaReserva;
    }

    /**
     * seta uma fila de reserva
     * @param filaReserva lista de reserva
     */
    public void setFilaReserva( Queue<Integer> filaReserva ) {
        this.filaReserva = filaReserva;
    }

    /**
     * retorna a data de reserva do livro
     * @return data de reserva
     */
    public LocalDate getDataReserva() {
        return dataReserva;
    }

    /**
     * seta a data de reserva do livro
     * @param dataReserva data de reserva do livro
     */
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
