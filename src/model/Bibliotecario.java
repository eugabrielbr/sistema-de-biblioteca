package model;

import DAO.acervo.AcervoDAOmap;

public class Bibliotecario extends Pessoa {

    public Bibliotecario(String name, int ID) {
        super(name, ID);
    }

    public void registroLivro(Livro livro){
        AcervoDAOmap registro = new AcervoDAOmap();
        registro.create(livro);
    }

    public void registroEmprestimo(){

        ////
    }


}

