package test.modeltest.functest;

import main.dao.DAO;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.emprestimo.EmprestimoDAOmap;
import main.dao.livro.LivroDAO;
import main.dao.livro.LivroDAOmap;
import main.dao.usuario.UsuarioDAO;
import main.dao.usuario.UsuarioDAOmap;
import main.exceptions.dao.DAOExceptions;
import main.exceptions.usecases.BlibUseCaseExceptions;
import main.exceptions.usecases.UsuarioUseCasesExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;
import main.model.func.BlibUseCases;
import main.model.func.UsuarioUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.sql.SQLOutput;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioUseCasesTest {

    LivroDAOmap livrodao = new LivroDAOmap();
    UsuarioDAOmap usuariodao = new UsuarioDAOmap();
    BlibUseCases obj = new BlibUseCases();
    EmprestimoDAOmap emprestimodao = new EmprestimoDAOmap();
    UsuarioUseCases objUsuarioUseCases = new UsuarioUseCases();



    //nao testei situacoes de multa ou bloqueio do usuario pq os testes dos metodos responsaveis estao localizados na classe BibliUseCasesTest
    public Emprestimo criandoEmprestimo( Integer idUsuario, Integer idlivro) throws DAOExceptions {


        Usuario user = usuariodao.findById(idUsuario);
        Livro livro = livrodao.findById(idlivro);
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        return emprestimo;

    }

    public void criandoUserLivro(){

        Usuario user = new Usuario("gabriel", "undefined", "00000000");
        Livro livro = new Livro("teoria da relativida","albert einstein",null,2222,"fisica",null);
        livrodao.create(livro);
        usuariodao.create(user);
    }

    @AfterEach
    public void tearDown() throws DAOExceptions {

        livrodao.deleteMany();
        usuariodao.deleteMany();
        emprestimodao.deleteMany();

    }

    @Test
    void reservarLivros() throws DAOExceptions, UsuarioUseCasesExceptions {

        //TESTANDO RESERVAR LIVRO SEM PENDENCIAS

        criandoUserLivro(); //registrando usuario e livro
        LocalDate dataLocal = LocalDate.of(2023,9,20); //definindo uma data para reserva

        objUsuarioUseCases.reservarLivros(1,1,dataLocal,livrodao,usuariodao); //realizando reserva
        Assertions.assertEquals(livrodao.findById(1).primeiroFila(),1); //verificando se o usuario q reservou é o primeiro da fila
        Assertions.assertEquals(LocalDate.of(2023,9,20).plusDays(7),livrodao.findById(1).getDataReserva()); //verificando se o prazo para o usuario ir buscar o livro foi atualizada

        //TESTANDO RESERVAR LIVRO COM ALGUMA PENDENCIA

        criandoUserLivro();//registrando novo usuario e livro
        Usuario usuario2 = usuariodao.findById(2); //buscando o segundo usuario
        usuario2.setBloqueio(true); //bloqueando ele
        usuariodao.update(usuario2,2); //atualizando no dao

        try {
            objUsuarioUseCases.reservarLivros(1, 2, dataLocal, livrodao, usuariodao); //tentando reservar livro mesmo usuario bloqueado
        }catch (UsuarioUseCasesExceptions e){
            Assertions.assertEquals(UsuarioUseCasesExceptions.ERRO_RESERVA + " - ID: " + 1,e.getMessage()); //excecao que de ser lancada
        }

        //TENTANDO RESERVAR MESMO QUE USUARIO JA TENHA RESERVADO

        try {
            objUsuarioUseCases.reservarLivros(1, 1, dataLocal, livrodao, usuariodao);//tentando reserva mesmo ja reservado
        }catch (UsuarioUseCasesExceptions e){
            Assertions.assertEquals(UsuarioUseCasesExceptions.ERRO_RESERVA + " - ID: " + 1,e.getMessage()); //excecao que de ser lancada
        }


    }

    @Test
    void renovarLivros() throws DAOExceptions, BlibUseCaseExceptions, UsuarioUseCasesExceptions {

        //TESTANDO RENOVAR EMPRESTIMO SEM PENDENCIAS
        criandoUserLivro();//registrando usuario e livro
        Emprestimo emprestimo = criandoEmprestimo(1,1);
        LocalDate dataLocal = LocalDate.of(2023,9,20); //a devolucao é dia 21, ele vai fazer a renovacao um dia antes

        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal); //emprestimo feito

        objUsuarioUseCases.renovarLivros(1,1,dataLocal,livrodao,usuariodao,emprestimodao); //solicitando renovacao

        Assertions.assertEquals(LocalDate.of(2023,9,28),emprestimo.getDataDevolucao()); //verificando se a data de devolucao somou mais sete dias
        //data de devolucao (21) + sete dias = 28
        Assertions.assertEquals(true,emprestimodao.findById(1).getFoiRenovado()); //verificando se contou a renovacao no objeto emprestimo

        //TESTANDO RENOVACAO MESMO JA RENOVADO UMA VEZ

        try{
            objUsuarioUseCases.renovarLivros(1,1,dataLocal,livrodao,usuariodao,emprestimodao); //tentando renovar novamente
        }catch (UsuarioUseCasesExceptions e){
            Assertions.assertEquals(UsuarioUseCasesExceptions.ERRO + " - ID: " + usuariodao.findById(1).getID(), e.getMessage()); //excecao que deve retornar
        }

        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //TESTANDO RENOVAR COM PESSOAS NA RESERVA

        criandoUserLivro();//registrando usuario e livro
        criandoUserLivro();
        Emprestimo emprestimoy = criandoEmprestimo(1,1);

        obj.registroEmprestimo(emprestimoy,livrodao,usuariodao,emprestimodao,dataLocal); //emprestimo feito
        objUsuarioUseCases.reservarLivros(1,2,dataLocal,livrodao,usuariodao); //outro usuario reserva o livro

        try{
            objUsuarioUseCases.renovarLivros(1,1,dataLocal,livrodao,usuariodao,emprestimodao); //tentando renovar com usuario2 na reserva
        }catch (UsuarioUseCasesExceptions e){
            Assertions.assertEquals(UsuarioUseCasesExceptions.ERRO + " - ID: " + usuariodao.findById(1).getID(), e.getMessage()); //excecao que deve retornar
        }


    }
}