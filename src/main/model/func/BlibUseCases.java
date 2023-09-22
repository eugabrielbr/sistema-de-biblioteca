package main.model.func;

import main.dao.DAO;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAO;
import main.exceptions.crud.CrudExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.List;

public class BlibUseCases {

    UsuarioDAO daoUsuario = DAO.getUsuarioDAO(); //singleton usuario
    LivroDAO daoLivro = DAO.getLivroDAO();
    EmprestimoDAO daoEmprestimo = DAO.getEmprestimoDAO();
    public void registroEmprestimo( Emprestimo emprestimo) throws CrudExceptions {

        atualizarMulta(emprestimo.getUsuario().getID());

        Livro resultLivro = daoLivro.findById(emprestimo.getLivro().getID()); //buscando o livro que vai ser emprestado

        if (resultLivro.getDisponibilidade() && !emprestimo.getUsuario().getBloqueio() && emprestimo.getUsuario().getDataDaMulta().isBefore(LocalDate.now())) {       //FALTA GERENCIAR AS MULTAS

            //Emprestimo emprestimo = new Emprestimo(usuario, dataEmprestimo, dataDevolucao, resultLivro); //criando o emprestimo
            daoEmprestimo.create(emprestimo); //registro emprestimo

            resultLivro.setDisponibilidade(false); //atualizo disponibilidade do livro
            daoLivro.update(resultLivro, resultLivro.getID()); //jogo no acervo

            Usuario user = daoUsuario.findById(emprestimo.getUsuario().getID());
            user.registroHistoricoUser(emprestimo);//registrar emprestimo no historico do usuario
            Integer temp = user.getID(); //guarda id temporario
            daoUsuario.update(user, temp); // atualiza usuario com novo emprestimo

        } else {
            //
        }

    }

    public void registroDevolucao(Integer IDlivro, Integer usuario) throws CrudExceptions {

        atualizarMulta(usuario);

        List<Emprestimo> lista = daoEmprestimo.findByUser(usuario); //lista com emprestimos do usuario que correspondem ao seu id

        Livro livro = daoLivro.findById(IDlivro); //busca do livro para att status
        livro.setDisponibilidade(true);
        daoLivro.update(livro,IDlivro);

        Usuario user = daoUsuario.findById(usuario); //registrar devolucao no historico do usuario
        for (Emprestimo x : lista){

            if (x.getLivro().getID() == livro.getID()){
                user.registrosLivrosDevolvidos(x);
                daoEmprestimo.delete(x.getIDemprestimo());
                break;
            }
        }

        daoUsuario.update(user,usuario); //atualiza o objeto com o historico do usuario


    }

    public void atualizarMulta(Integer IDusuario) throws CrudExceptions {

        List<Emprestimo> lista = daoEmprestimo.findByUser(IDusuario);
        List<LocalDate> listAux = new ArrayList<>();
        LocalDate dataRecente = listAux.get(0);

        long diasAtrasado = 0;

        for (Emprestimo x : lista){ //calculando dias de multa

            if (x.getDataDevolucao().isBefore(LocalDate.now())){

                long diasAtraso = ChronoUnit.DAYS.between(x.getDataDevolucao(),LocalDate.now());
                diasAtraso = Math.abs(diasAtraso);
                diasAtrasado += diasAtraso;
                listAux.add(x.getDataDevolucao());
            }

        }

        for (LocalDate y : listAux){ //pegando a data mais recente de devolucao

            if (y.isAfter(dataRecente)){

                dataRecente = y;
            }
        }

        Usuario temp = daoUsuario.findById(IDusuario); //atualizando no user do usuario
        temp.setDataDaMulta(dataRecente.plusDays(diasAtrasado));
        daoUsuario.update(temp,temp.getID());




    }

}
