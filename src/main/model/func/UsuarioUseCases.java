package main.model.func;

import main.dao.DAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAO;
import main.exceptions.crud.DAOExceptions;
import main.model.Livro;
import main.model.Usuario;

import java.time.LocalDate;

public class UsuarioUseCases {

    LivroDAO daoLivro = DAO.getLivroDAO();
    UsuarioDAO daoUsuario = DAO.getUsuarioDAO();


    public void reservarLivros( Integer IDlivro, Integer IDusuario, LocalDate dataLocal,LivroDAO  daoLivro, UsuarioDAO daoUsuario) throws DAOExceptions {


        Livro livro = daoLivro.findById(IDlivro);
        BlibUseCases obj = new BlibUseCases();
        obj.atualizarReserva(IDlivro,daoLivro,dataLocal);
        Usuario usuario = daoUsuario.findById(IDusuario);

        if (!usuario.getBloqueio() && usuario.getDataDaMulta().isBefore(dataLocal)) {
            livro.adicionarReserva(IDusuario);
        }
        if (livro.getDisponibilidade()){
            livro.setDataReserva(dataLocal.plusDays(7));
        }

        daoLivro.update(livro,IDlivro);

    }
}
