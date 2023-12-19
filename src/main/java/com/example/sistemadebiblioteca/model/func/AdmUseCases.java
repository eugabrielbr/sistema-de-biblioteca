package main.model.func;

import main.dao.DAO;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.livro.LivroDAO;
import main.dao.usuario.UsuarioDAO;
import main.exceptions.dao.DAOExceptions;

import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.Map.Entry;

/**
 * Classe com metodos dos casos de uso do administrador
 * @author Gabriel
 */
public class AdmUseCases {

    public AdmUseCases() throws IOException, ClassNotFoundException {
    }

    /**
     * conta e retorna o numero de livros emprestados
     * @param daoEmprestimo objeto do dao do emprestimo
     * @return quantidade de livros emprestados
     */
    public Integer numeroLivrosEmprestados(EmprestimoDAO daoEmprestimo){

        Integer quant = 0;

        for (Entry<Integer,Emprestimo> x : daoEmprestimo.findMany().entrySet()){
            quant++;
        }
        return quant;
    }

    /**
     * conta e retorna a quantidadade de livros atrasados
     * @param daoEmprestimo objeto do dao do emprestimo
     * @param dataLocal data do sistema
     * @return quantidade de livros atrasados
     */
    public Integer qtdLivrosAtrasados(EmprestimoDAO daoEmprestimo,LocalDate dataLocal){

        Integer quant = 0;

        for (Entry<Integer,Emprestimo> x : daoEmprestimo.findMany().entrySet()){

            if (x.getValue().getDataDevolucao() != null && x.getValue().getDataDevolucao().isBefore(dataLocal)){


                quant++;
            }

        }
        return quant;

    }

    /**
     * busca e retorna a lista de livros mais populares em ordem
     * @param daoLivro objeto do dao do livro
     * @return lista de livros
     */
    public List<Livro> livrosMaisPopulares(LivroDAO daoLivro){

        Map<Integer,Livro> map = daoLivro.findMany();
        List<Livro> lista = new ArrayList<>();

        for(Entry<Integer,Livro> x : map.entrySet()){
            lista.add(x.getValue());
        }

        Comparator<Livro> compararEmprestimos = Comparator.comparingInt(Livro::getQtdEmprestimo).reversed();
        Collections.sort(lista,compararEmprestimos);

        if (!lista.isEmpty()){
            return lista; //retorna lista do maior para o menor de acordo com o numero de emprestimo
        } else{
            return null;
        }


    }

    /**
     * busca e retorna a quantidade de livros atrasados
     * @param daoLivro objeto do dao do livro
     * @return quantidade de livros atrasados
     */
    public Integer qtdLivrosReservados(LivroDAO daoLivro){

        Integer quant = 0;

        for (Entry<Integer, Livro> x : daoLivro.findMany().entrySet()){

            if (!x.getValue().getFilaReserva().isEmpty()){
                quant++;
            }

        }
        return quant;

    }

    /**
     * busca e retorna e historico de emprestimos do usuario
     * @param usuario objeto do usuario
     * @param daoUsuario objeto do dao do usuario
     * @return lista do historico de emprestimos
     * @throws DAOExceptions excecao do dao
     */
    public List<Emprestimo> emprestimoUsuario(Integer usuario,UsuarioDAO daoUsuario) throws DAOExceptions {

        List<Emprestimo> lista = daoUsuario.findById(usuario).getHistorico();
        return lista;
    }

    /**
     * bloqueia e desbloqueia usuario
     * @param ID id do usuario
     * @param option opcao entre desbloquear e bloquear
     * @param daoUsuario objeto do dao do usuario
     * @throws DAOExceptions exececao do dao
     */
    public void bloquearDesbloquearUsuario( Integer ID, Integer option,UsuarioDAO daoUsuario) throws DAOExceptions, IOException, ClassNotFoundException {

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
