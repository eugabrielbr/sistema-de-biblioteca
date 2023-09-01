package model;
import DAO.AcervoDAOmap;

import java.util.LinkedList;
import java.util.List;
public class Pessoa {
    
    private String name;
    private int ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Livro> pesquisarLivros(String titulo, String autor, Integer ISBN, String categ){

        AcervoDAOmap obj = new AcervoDAOmap();
        return obj.findByGeneral(obj.acervo, titulo, autor, ISBN,categ);
    }




}
