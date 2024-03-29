package modeltest.functest;

import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAO;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAOmap;
import com.example.sistemadebiblioteca.dao.livro.LivroDAO;
import com.example.sistemadebiblioteca.dao.livro.LivroDAOmap;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAO;
import com.example.sistemadebiblioteca.dao.usuario.UsuarioDAOmap;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.exceptions.usecases.BlibUseCaseExceptions;
import com.example.sistemadebiblioteca.exceptions.usecases.UsuarioUseCasesExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Usuario;
import com.example.sistemadebiblioteca.model.BlibUseCases;
import com.example.sistemadebiblioteca.model.UsuarioUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioUseCasesTest {

    LivroDAO livrodao = DAO.getLivroDAO();//SE DER MERDA VOLTAR PARA LIVRODAOMAP
    UsuarioDAO usuariodao = DAO.getUsuarioDAO();
    BlibUseCases obj = new BlibUseCases();
    EmprestimoDAO emprestimodao = DAO.getEmprestimoDAO();
    UsuarioUseCases objUsuarioUseCases = new UsuarioUseCases();

    UsuarioUseCasesTest() throws IOException, ClassNotFoundException {
    }


    //nao testei situacoes de multa ou bloqueio do usuario pq os testes dos metodos responsaveis estao localizados na classe BibliUseCasesTest
    public Emprestimo criandoEmprestimo( Integer idUsuario, Integer idlivro) throws DAOExceptions {


        Usuario user = usuariodao.findById(idUsuario);
        Livro livro = livrodao.findById(idlivro);
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        return emprestimo;

    }

    public void criandoUserLivro() throws IOException, ClassNotFoundException {

        Usuario user = new Usuario("gabriel", "undefined", "00000000","teste");
        Livro livro = new Livro("teoria da relativida","albert einstein",null,2222,"fisica",null);
        livrodao.create(livro);
        usuariodao.create(user);
    }

    @AfterEach
    public void tearDown() throws DAOExceptions, IOException, ClassNotFoundException {

        livrodao.deleteMany();
        usuariodao.deleteMany();
        emprestimodao.deleteMany();

    }

    @Test
    void reservarLivros() throws DAOExceptions, UsuarioUseCasesExceptions, IOException, ClassNotFoundException {

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
    void renovarLivros() throws DAOExceptions, BlibUseCaseExceptions, UsuarioUseCasesExceptions, IOException, ClassNotFoundException {

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