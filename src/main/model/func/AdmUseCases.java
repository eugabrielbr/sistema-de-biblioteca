package main.model.func;

import main.dao.DAO;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAO;
import main.exceptions.crud.DAOExceptions;

import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

public class AdmUseCases {

    UsuarioDAO daoUsuario = DAO.getUsuarioDAO(); //singleton usuario
    LivroDAO daoLivro = DAO.getLivroDAO();
    EmprestimoDAO daoEmprestimo = DAO.getEmprestimoDAO();
    public Integer numeroLivrosEmprestados(){

        Integer quant = 0;

        for (Entry<Integer,Emprestimo> x : daoEmprestimo.findMany().entrySet()){

            quant++;
        }
        return quant;
    }

    public Integer qtdLivrosAtrasados(){

        Integer quant = 0;

        for (Entry<Integer,Emprestimo> x : daoEmprestimo.findMany().entrySet()){

            if (x.getValue().getDataDevolucao() != null && x.getValue().getDataDevolucao().isBefore(LocalDate.now())){
                quant++;
            }

        }
        return quant;

    }

    public Integer qtdLivrosReservados(){

        Integer quant = 0;

        for (Entry<Integer, Livro> x : daoLivro.findMany().entrySet()){

            if (!x.getValue().getFilaReserva().isEmpty()){
                quant++;
            }

        }
        return quant;

    }

    public List<Emprestimo> emprestimoUsuario(Integer usuario) throws DAOExceptions {

        List<Emprestimo> lista = daoUsuario.findById(usuario).getHistorico();
        return lista;
    }

    public void bloquearDesbloquearUsuario( Integer ID, Integer option) throws DAOExceptions {

        //1 - bloquear
        //2 - desbloquear

        Usuario findUsu = daoUsuario.findById(ID);
        Integer temp = findUsu.getID();

        if (option == 1){
            findUsu.setBloqueio(true);
        }
        else if (option == 2){
            findUsu.setBloqueio(false);
        }

        daoUsuario.update(findUsu, temp);

    }
}
