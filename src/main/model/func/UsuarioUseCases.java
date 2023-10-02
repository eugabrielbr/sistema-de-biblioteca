package main.model.func;

import main.dao.DAO;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAO;
import main.exceptions.dao.DAOExceptions;
import main.exceptions.usecases.UsuarioUseCasesExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;

import java.time.LocalDate;
/**
 * Classe com metodos dos casos de uso do usuario
 * @author Gabriel
 */
public class UsuarioUseCases {
    /**
     * objeto do dao do livro
     */
    LivroDAO daoLivro = DAO.getLivroDAO();
    /**
     * objeto do dao do usuario
     */
    UsuarioDAO daoUsuario = DAO.getUsuarioDAO();
    /**
     * objeto do dao do emprestimo
     */
    EmprestimoDAO daoEmprestimo = DAO.getEmprestimoDAO();

    /**
     * registra e atualiza informacoes referentes a reserva de livros
     * @param IDlivro id do livro
     * @param IDusuario id do usuario
     * @param dataLocal data do sistema
     * @param daoLivro objeto do dao do livro
     * @param daoUsuario objeto do dao do usuario
     * @throws DAOExceptions excecao dao
     * @throws UsuarioUseCasesExceptions excecao usuariousecases
     */
    public void reservarLivros( Integer IDlivro, Integer IDusuario, LocalDate dataLocal,LivroDAO  daoLivro, UsuarioDAO daoUsuario) throws DAOExceptions, UsuarioUseCasesExceptions {


        Livro livro = daoLivro.findById(IDlivro);
        BlibUseCases obj = new BlibUseCases();
        Usuario usuario = daoUsuario.findById(IDusuario);
        boolean verificacao = false;

        for(Integer x: livro.getFilaReserva()){

            if(x == usuario.getID()){
                verificacao = true;
            }
        }


        if (!usuario.getBloqueio() && !verificacao && (usuario.getDataDaMulta() == null || usuario.getDataDaMulta().isBefore(dataLocal))) {
            livro.adicionarReserva(IDusuario);
            obj.atualizarReserva(IDlivro,daoLivro,dataLocal);
        }
        else{
            throw new UsuarioUseCasesExceptions(UsuarioUseCasesExceptions.ERRO_RESERVA,livro.getID());
        }

        daoLivro.update(livro,IDlivro);

    }

    /**
     *
     * @param IDlivro id do livro
     * @param IDusuario id do usuario
     * @param dataLocal data do sistema
     * @param daoLivro objeto do dao do livro
     * @param daoUsuario objeto do dao do usuario
     * @param daoEmprestimo objeto do dao do emprestimo
     * @throws DAOExceptions excecao dao
     * @throws UsuarioUseCasesExceptions excecao usuariousecases
     */
    public void renovarLivros(Integer IDlivro, Integer IDusuario,LocalDate dataLocal,LivroDAO daoLivro,UsuarioDAO daoUsuario,EmprestimoDAO daoEmprestimo) throws DAOExceptions, UsuarioUseCasesExceptions {

        BlibUseCases obj = new BlibUseCases();
        obj.atualizarReserva(IDlivro,daoLivro,dataLocal);
        obj.atualizarMulta(IDusuario,daoEmprestimo,daoUsuario,dataLocal);
        Usuario usuario = daoUsuario.findById(IDusuario);
        Livro livro = daoLivro.findById(IDlivro);
        Emprestimo emprestimo = daoEmprestimo.findByIDlivroIDusuario(IDlivro,IDusuario);

        if (livro.getFilaReserva().isEmpty() && !usuario.getBloqueio() && !emprestimo.getFoiRenovado() && (usuario.getDataDaMulta() == null || usuario.getDataDaMulta().isBefore(dataLocal))){

            emprestimo.setFoiRenovado(true);
            emprestimo.setDataDevolucao(emprestimo.getDataDevolucao().plusDays(7));
            daoEmprestimo.update(emprestimo,emprestimo.getIDemprestimo());

        }
        else{

            throw new UsuarioUseCasesExceptions(UsuarioUseCasesExceptions.ERRO,emprestimo.getLivro().getID());
        }
    }
}
