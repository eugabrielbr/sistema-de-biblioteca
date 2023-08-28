public class Livro {

    private String titulo;
    private String autor;
    private String editora;
    private int ISBN;
    private int categoria;
    private boolean disponibilidade;
    private String localizacao;

    public void setInfos(String titulo, String autor, String editora, int ISBN, int categoria, boolean disponibilidade, String localizacao) {

        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.disponibilidade = disponibilidade;
        this.localizacao = localizacao;

    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }


    public String getEditora() {
        return editora;
    }

    public int getISBN() {
        return ISBN;
    }

    public int getCategoria() {
        return categoria;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }
}
