package com.example.sistemadebiblioteca.model;

import com.example.sistemadebiblioteca.dao.livro.LivroDAO;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAO;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAO;
import com.example.sistemadebiblioteca.exceptions.usecases.BlibUseCaseExceptions;
import com.example.sistemadebiblioteca.model.Usuario;


import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe com metodos dos casos de uso do bibliotecario
 * @author Gabriel
 */
public class BlibUseCases {


    public BlibUseCases() throws IOException, ClassNotFoundException {

    }


    /**
     * atualiza a reserva do livro
     * @param IDLivro id do livro
     * @param daoLivro objeto do dao do livro
     * @param dataLocal data do sistema
     * @throws DAOExceptions excecao do dao
     */
    public void atualizarReserva(Integer IDLivro,LivroDAO daoLivro, LocalDate dataLocal) throws DAOExceptions, IOException, ClassNotFoundException {

        Livro livro = daoLivro.findById(IDLivro);

        if (!livro.getFilaReserva().isEmpty()){

            if (livro.getDataReserva() == null || livro.getDataReserva().isBefore(dataLocal)) {

                if (livro.getDataReserva() != null) {
                    livro.removerFila();//remover da lista
                }

                if (livro.getDisponibilidade() && !livro.getFilaReserva().isEmpty()){

                    livro.setDataReserva(dataLocal.plusDays(7));

                }

            }
        }
        else{
            livro.setDataReserva(null);
        }
        daoLivro.update(livro,IDLivro);

    }

    /**
     * verifica quantos emprestimos ativos o usuario tem
     * @param usuario objeto usuario
     * @param daoEmprestimo objeto do dao do emprestimo
     * @return true ou false
     */
    public boolean qtdEmprestimos( Integer usuario, EmprestimoDAO daoEmprestimo){

        List<Emprestimo> lista = daoEmprestimo.findByUser(usuario);

        if (lista.size() >= 3){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * registra emprestimo e atualiza dados do usuario, livro e emprestimo
     * @param emprestimo objeto do emprestimo
     * @param daoLivro objeto do dao do livro
     * @param daoUsuario objeto do dao do usuario
     * @param daoEmprestimo objeto do dao do emprestimo
     * @param dataLocal data do sistema
     * @throws DAOExceptions excecao dao
     * @throws BlibUseCaseExceptions excecao blibusecases
     */
    public void registroEmprestimo( Emprestimo emprestimo, LivroDAO daoLivro, UsuarioDAO daoUsuario, EmprestimoDAO daoEmprestimo, LocalDate dataLocal) throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        atualizarMulta(emprestimo.getUsuario().getID(), daoEmprestimo,daoUsuario,dataLocal);
        atualizarReserva(emprestimo.getLivro().getID(),daoLivro,emprestimo.getDataEmprestimo());
        Usuario user1 = daoUsuario.findById(emprestimo.getUsuario().getID());
        boolean qtdEmprestimosAtivos = qtdEmprestimos(user1.getID(),daoEmprestimo);

        Livro resultLivro = daoLivro.findById(emprestimo.getLivro().getID()); //buscando o livro que vai ser emprestado


        if (qtdEmprestimosAtivos) {
            //System.out.println("if 1");
            if ((emprestimo.getLivro().primeiroFila() == null || emprestimo.getLivro().primeiroFila().equals(emprestimo.getUsuario().getID())) || emprestimo.getLivro().primeiroFila() == null) {
                //System.out.println("if 2");
                if (emprestimo.getLivro().getDisponibilidade() && !emprestimo.getUsuario().getBloqueio()) {
                    //System.out.println("if 3");
                    if ((user1.getDataDaMulta() == null) || user1.getDataDaMulta().isBefore(emprestimo.getDataEmprestimo())) {
                        //System.out.println("if 4");
                        daoEmprestimo.create(emprestimo); //registro emprestimo

                        resultLivro.setDisponibilidade(false); //atualizo disponibilidade do livro
                        resultLivro.removerFila();//remover da fila, pois ele ja pegou
                        resultLivro.setarMaisUm(); //setando a quantidade de vezes que o livro foi pego emprestado
                        daoLivro.update(resultLivro, resultLivro.getID()); //jogo no acervo
                        Usuario user = daoUsuario.findById(emprestimo.getUsuario().getID());

                        user.registroHistoricoUser(emprestimo);//registrar emprestimo no historico do usuario
                        user.somarEmprestimos();
                        user.setDataDaMulta(null);
                        Integer temp = user.getID(); //guarda id temporario
                        daoUsuario.update(user, temp); // atualiza usuario com novo emprestimo

                    } else {

                        throw new BlibUseCaseExceptions(BlibUseCaseExceptions.MULTADO,  user1.getID());
                    }
                }
                else{

                    if (!emprestimo.getLivro().getDisponibilidade()){

                        throw new BlibUseCaseExceptions(BlibUseCaseExceptions.INDISPONIVEL, resultLivro.getID());

                    }
                    else if(emprestimo.getUsuario().getBloqueio()){

                        throw new BlibUseCaseExceptions(BlibUseCaseExceptions.BLOQUEADO, user1.getID());

                    }

                }

            } else {

                throw new BlibUseCaseExceptions(BlibUseCaseExceptions.RESERVADO, resultLivro.getID());

            }
        }
        else {
            throw new BlibUseCaseExceptions(BlibUseCaseExceptions.ATIVOS, user1.getID());
        }

    }

    /**
     * registra a devolucao do usuario e atualiza informacaoes
     * @param IDlivro id do livro
     * @param usuario objeto usuario
     * @param daoEmprestimo objeto do dao do emprestimo
     * @param daoLivro objeto do dao do livro
     * @param daoUsuario objeto do dao do usuario
     * @param dataLocal data do sistema
     * @throws DAOExceptions excecao dao
     * @throws BlibUseCaseExceptions excecao biblibusecases
     */
    public void registroDevolucao(Integer IDlivro, Integer usuario,EmprestimoDAO daoEmprestimo,LivroDAO daoLivro,UsuarioDAO daoUsuario,LocalDate dataLocal) throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        atualizarMulta(usuario,daoEmprestimo,daoUsuario,dataLocal);

        Livro livro = daoLivro.findById(IDlivro); //busca do livro para att status

        Usuario user = daoUsuario.findById(usuario); //registrar devolucao no historico do usuario

        try{

            Emprestimo emprestimo = daoEmprestimo.findByIDlivroIDusuario(IDlivro,usuario);
            user.registrosLivrosDevolvidos(emprestimo);
            daoEmprestimo.delete(emprestimo.getIDemprestimo());
            livro.setDisponibilidade(true);

        }catch (DAOExceptions | IOException | ClassNotFoundException e){

            throw new BlibUseCaseExceptions(BlibUseCaseExceptions.FAILDEVOLUCAO, livro.getID());
        }

        if (!livro.getFilaReserva().isEmpty()){
            livro.setDataReserva(dataLocal.plusDays(7)); //dias que o reserva pode pegar o livro
        }
        else{
            livro.setDataReserva(null);
        }

        daoLivro.update(livro,IDlivro);
        daoUsuario.update(user,usuario); //atualiza o objeto com o historico do usuario


    }

    /**
     * calcula a atualiza a multa do usuario
     * @param IDusuario id do usuario
     * @param daoEmprestimo objeto do dao do emprestimo
     * @param daoUsuario objeto do dao do usuario
     * @param dataLocal data do sistema
     * @throws DAOExceptions excecao dao
     */
    public void atualizarMulta( Integer IDusuario, EmprestimoDAO daoEmprestimo, UsuarioDAO daoUsuario, LocalDate dataLocal) throws DAOExceptions, IOException, ClassNotFoundException {

        List<Emprestimo> lista = daoEmprestimo.findByUser(IDusuario);
        List<LocalDate> listAux = new ArrayList<>();
        Usuario temp = daoUsuario.findById(IDusuario);


        long diasAtrasado = 0;


        for (Emprestimo x : lista){ //calculando dias de multa

            if (x.getDataDevolucao().isBefore(dataLocal)){

                long diasAtraso = ChronoUnit.DAYS.between(x.getDataDevolucao(),dataLocal);
                diasAtraso = Math.abs(diasAtraso);
                diasAtrasado += diasAtraso;
                listAux.add(x.getDataDevolucao());
            }

        }

        if (!listAux.isEmpty()) {

            LocalDate dataRecente = listAux.get(0);

            for (LocalDate y : listAux) { //pegando a data mais recente de devolucao

                if (y.isAfter(dataRecente)) {

                    dataRecente = y;
                }
            }
            diasAtrasado = diasAtrasado * 2;
            temp.setDataDaMulta(dataRecente.plusDays(diasAtrasado));
            daoUsuario.update(temp, temp.getID());

        }


    }

}
