package DAO.acervo;

import DAO.CRUD;
import model.Livro;
import model.Pesquisa;

import java.util.List;

interface AcervoDAO extends CRUD<Livro> {

    public List<Livro> findByGeneral(Pesquisa pesquisa);
}
