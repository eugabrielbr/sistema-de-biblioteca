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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.List;
//LEMBRAR DE MANDAR PARA AS FUNCOES OS OBJETOS DO SIGLETON
public class BlibUseCases {

    UsuarioDAO daoUsuario = DAO.getUsuarioDAO(); //singleton usuario
    LivroDAO daoLivro = DAO.getLivroDAO();
    EmprestimoDAO daoEmprestimo = DAO.getEmprestimoDAO();

    //TODOS OS MESSAGES SAO EXCECOES
    public void atualizarReserva(Integer IDLivro,LivroDAO daoLivro, LocalDate dataLocal) throws DAOExceptions {

        Livro livro = daoLivro.findById(IDLivro);

        if (!livro.getFilaReserva().isEmpty()){

            if (livro.getDataReserva().isBefore(dataLocal)) {

                livro.removerFila();//remover da lista

                if (livro.getDisponibilidade() && !livro.getFilaReserva().isEmpty()){

                    livro.setDataReserva(dataLocal.plusDays(7));

                }
                else{
                    livro.setDataReserva(null);

                }

            }
        }
        else{
            livro.setDataReserva(null);
        }
        daoLivro.update(livro,IDLivro);

    }

    public boolean qtdEmprestimos(Integer usuario,EmprestimoDAO daoEmprestimo){

        List<Emprestimo> lista = daoEmprestimo.findByUser(usuario);

        if (lista.size() >= 3){
            return false;
        }
        else{
            return true;
        }
    }


    public void registroEmprestimo( Emprestimo emprestimo, LivroDAO daoLivro, UsuarioDAO daoUsuario, EmprestimoDAO daoEmprestimo, LocalDate dataLocal) throws DAOExceptions {

        atualizarMulta(emprestimo.getUsuario().getID(), daoEmprestimo,daoUsuario,dataLocal);
        atualizarReserva(emprestimo.getLivro().getID(),daoLivro,dataLocal);
        Usuario user1 = daoUsuario.findById(emprestimo.getUsuario().getID());
        boolean qtdEmprestimosAtivos = qtdEmprestimos(user1.getID(),daoEmprestimo);

        Livro resultLivro = daoLivro.findById(emprestimo.getLivro().getID()); //buscando o livro que vai ser emprestado


        if (qtdEmprestimosAtivos == true) {
            //System.out.println("if 1");
            if (emprestimo.getLivro().primeiroFila() == emprestimo.getUsuario().getID() || emprestimo.getLivro().primeiroFila() == null) {
                //System.out.println("if 2");
                if (emprestimo.getLivro().getDisponibilidade() && !emprestimo.getUsuario().getBloqueio()) {
                    //System.out.println("if 3");
                    if ((user1.getDataDaMulta() == null) || user1.getDataDaMulta().isBefore(dataLocal)) {
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

                        String message = "Usuario multado";
                    }
                }
            } else {
                String message = "Livro ja reservado por outro usuario";

            }
        }
        else{
            String message = "Usuario com 3 emprestimos ja ativos";
        }

    }

    public void registroDevolucao(Integer IDlivro, Integer usuario,EmprestimoDAO daoEmprestimo,LivroDAO daoLivro,UsuarioDAO daoUsuario,LocalDate dataLocal) throws DAOExceptions {

        atualizarMulta(usuario,daoEmprestimo,daoUsuario,dataLocal);

        List<Emprestimo> lista = daoEmprestimo.findByUser(usuario); //lista com emprestimos do usuario que correspondem ao seu id

        Livro livro = daoLivro.findById(IDlivro); //busca do livro para att status



        if (!livro.getFilaReserva().isEmpty()){
            livro.setDataReserva(dataLocal.plusDays(7)); //dias que o reserva pode pegar o livro
        }
        else{
            livro.setDisponibilidade(true);
            livro.setDataReserva(null);
        }

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

    public void atualizarMulta( Integer IDusuario, EmprestimoDAO daoEmprestimo, UsuarioDAO daoUsuario, LocalDate dataLocal) throws DAOExceptions {

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
