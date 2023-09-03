package DAO.Acervo;

import DAO.CRUD;
import model.Livro;
import model.Pesquisa;

import java.util.List;
import java.util.Map;

interface AcervoDAO extends CRUD<Livro> {

    public List<Livro> findByGeneral(Pesquisa pesquisa);
}
