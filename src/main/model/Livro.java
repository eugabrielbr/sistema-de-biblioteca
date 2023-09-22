package main.model;

public class Livro {

    private String titulo;
    private String autor;
    private String editora;
    private Integer ISBN;
    private String categoria;
    private boolean disponibilidade;
    private String localizacao;
    private int ID;

    public Livro(String titulo, String autor, String editora, Integer ISBN, String categoria, String localizacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.disponibilidade = true;
        this.localizacao = localizacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
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

    public int getID() {
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
                '}';
    }
}
