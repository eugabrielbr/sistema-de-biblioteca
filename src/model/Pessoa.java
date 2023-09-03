package model;
import DAO.acervo.AcervoDAOmap;

import java.util.List;
public class Pessoa {
    
    private String name;
    private int ID;

    public Pessoa(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

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

    public List<Livro> pesquisarLivros(Pesquisa pesquisa){

        AcervoDAOmap obj = new AcervoDAOmap();
        return obj.findByGeneral(pesquisa);
    }




}
