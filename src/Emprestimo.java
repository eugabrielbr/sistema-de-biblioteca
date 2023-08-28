import java.time.LocalDate;

public class Emprestimo {

    private int IDusuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String tituloLivroEmprestado;
    private int ISBN;
    private String autor;
    private String editora;

    public void verStatusUsuarioLivro(){
        //
    }

    public void atualizarStatusLivro(){
        //
    }

    public int getIDusuario() {
        return IDusuario;
    }

    public void setIDusuario(int IDusuario) {
        this.IDusuario = IDusuario;
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

    public String getTituloLivroEmprestado() {
        return tituloLivroEmprestado;
    }

    public void setTituloLivroEmprestado(String tituloLivroEmprestado) {
        this.tituloLivroEmprestado = tituloLivroEmprestado;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
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
}
