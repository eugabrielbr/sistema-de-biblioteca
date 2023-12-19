package main.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe com atributos e metodos relacionados aos emprestimos
 * @author Gabriel
 */
public class Emprestimo implements Serializable {

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

    /**
     * retorna o objeto do usuario
     * @return objeto usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * seta o objeto usuario
     * @param usuario objeto do usuario
     */

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * retorna a data do emprestimo
     * @return data do emprestimo
     */
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * seta a data do emprestimo
     * @param dataEmprestimo data do emprestimo
     */
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * retorna a data de devolucao
     * @return data de devolucao
     */
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * seta a data de devolucao
     * @param dataDevolucao data de devolucao
     */
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * retorna o objeto livro
     * @return objeto livro
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * seta o objeto livro
     * @param livro objeto livro
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    /**
     * retorna o ID do emprestimo
     * @return ID do emprestimo
     */
    public int getIDemprestimo() {
        return IDemprestimo;
    }

    /**
     * seta o ID do emprestimo
     * @param IDemprestimo ID do emprestimo
     */
    public void setIDemprestimo( int IDemprestimo ) {
        this.IDemprestimo = IDemprestimo;
    }

    /**
     * retorna um booleano indicando a situacao de renovacao do emprestimo
     * @return situacao de renovacao do emprestimo
     */
    public boolean getFoiRenovado() {
        return foiRenovado;
    }

    /**
     * seta um booleano que indica a situaca de renovacao
     * @param foiRenovado situacao de renovacao do emprestimo
     */

    public void setFoiRenovado( boolean foiRenovado ) {
        this.foiRenovado = foiRenovado;
    }

    /**
     * retorna uma string formatada com as informacoes dos atributos do objeto emprestimo
     * @return string formatada com as informacoes dos atributos do objeto emprestimo
     */
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

