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
import com.example.sistemadebiblioteca.model.AdmUseCases;
import com.example.sistemadebiblioteca.model.BlibUseCases;
import com.example.sistemadebiblioteca.model.UsuarioUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdmUseCasesTest {
    /**
     * objeto da classe LivroDAOmap
     */
    LivroDAO livrodao = DAO.getLivroDAO();
    /**
     * objeto da classe UsuarioDAOmap
     */
    UsuarioDAO usuariodao = DAO.getUsuarioDAO();
    /**
     * objeto da classe BiblibUseCases
     */
    BlibUseCases obj = new BlibUseCases();
    /**
     * objeto da classe EmprestimoDAOmap
     */
    EmprestimoDAO emprestimodao = DAO.getEmprestimoDAO();
    /**
     * objeto da classe UsuarioUseCases
     */
    UsuarioUseCases objUsuarioUseCases = new UsuarioUseCases();
    /**
     * objeto da classe AdmUseCases
     */
    AdmUseCases objAdmUseCases = new AdmUseCases();

    AdmUseCasesTest() throws IOException, ClassNotFoundException {
    }

    /**
     * condicao inicial antes dos testes
     */
    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException, DAOExceptions {


        Livro livro1 = new Livro("Segredos do Universo", "Sarah Johnson", "Editora Imaginação", 1234, "Aventura", "Estante 1");
        Livro livro2 = new Livro("Aventuras nas Montanhas", "Lucas Silva", "Editora Imaginação", 5678, "Aventura", "Estante 2");
        Livro livro3 = new Livro("Negócios de Sucesso", "Maria Rodrigues", "Editora Empreendedora", 9012, "Negócios", "Estante 3");
        Livro livro4 = new Livro("A Magia da Poesia", "Ana Pereira", "Editora Versos Mágicos", 3456, "Poesia", "Estante 4");
        Livro livro5 = new Livro("O Segredo do Passado", "Pedro Almeida", "Editora Enigma", 7890, "Mistério", "Estante 5");

        livrodao.create(livro1); //registrando livros para testes
        livrodao.create(livro2);
        livrodao.create(livro3);
        livrodao.create(livro4);
        livrodao.create(livro5);

    }
    /**
     * condicao para o final de cada teste
     * @throws DAOExceptions excecoes do dao
     */
    @AfterEach
    public void tearDown() throws DAOExceptions, IOException, ClassNotFoundException {

        livrodao.deleteMany(); //deletando os dados
        usuariodao.deleteMany();
        emprestimodao.deleteMany();
    }

    /**
     * adicionando um usuario e livro no dao
     */
    public void criandoUserLivro() throws IOException, ClassNotFoundException {

        Usuario user = new Usuario("gabriel", "undefined", "00000000","teste");
        Livro livro = new Livro("teoria da relativida","albert einstein",null,2222,"fisica",null);
        livrodao.create(livro);
        usuariodao.create(user);
    }

    /**
     * criando um emprestimo
     * @param idUsuario id do usuario
     * @param idlivro id do livro
     * @return objeto emprestimo
     * @throws DAOExceptions excecoes do dao
     */
    public Emprestimo criandoEmprestimo( Integer idUsuario, Integer idlivro) throws DAOExceptions {


        Usuario user = usuariodao.findById(idUsuario);
        Livro livro = livrodao.findById(idlivro);
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        return emprestimo;

    }

    /**
     * testando a contagem de emprestimos ativos
     * @throws DAOExceptions excecoes do dao
     * @throws BlibUseCaseExceptions excecoes de BlibUseCaseExceptions
     */
    @Test
    void numeroLivrosEmprestados() throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        criandoUserLivro(); //adicionando 2 livros e 2 usuarios
        criandoUserLivro();

        Emprestimo emprestimo = criandoEmprestimo(1,1); //criando obj emprestimo
        Emprestimo emprestimo2 = criandoEmprestimo(2,2);

        LocalDate dataLocal = LocalDate.of(2023,9,11); //definindo uma data do sistema aleatoria

        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal); //registrando os dois emprestimos
        obj.registroEmprestimo(emprestimo2,livrodao,usuariodao,emprestimodao,dataLocal);

        Assertions.assertEquals(objAdmUseCases.numeroLivrosEmprestados(emprestimodao),2); //verificando se a contagem foi correta

    }

    /**
     * testando a contagem de livros atrasados
     * @throws DAOExceptions excecoes do dao
     * @throws BlibUseCaseExceptions excecoes de BlibUseCaseExceptions
     */
    @Test
    void qtdLivrosAtrasados() throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        criandoUserLivro(); //adicionando 2 livros e 2 usuarios
        criandoUserLivro();

        Emprestimo emprestimo = criandoEmprestimo(1,1); //criando obj emprestimo
        Emprestimo emprestimo2 = criandoEmprestimo(2,2);

        LocalDate dataLocal = LocalDate.of(2023,9,25); //definindo uma data do sistema que vai causar atraso na devolucao

        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal); //registrando os dois emprestimos
        obj.registroEmprestimo(emprestimo2,livrodao,usuariodao,emprestimodao,dataLocal);

        Assertions.assertEquals(objAdmUseCases.qtdLivrosAtrasados(emprestimodao,dataLocal),2);//verificando se a contagem foi correta
    }

    /**
     * testando a RegistrarLivroController de livros mais populares
     * @throws DAOExceptions excecoes do dao
     * @throws BlibUseCaseExceptions excecoes de BlibUseCaseExceptions
     */
    @Test
    void livrosMaisPopulares() throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        criandoUserLivro(); //adicionando 2 livros e 2 usuarios
        criandoUserLivro();

        Emprestimo emprestimo = criandoEmprestimo(1,1); //criando obj emprestimo
        Emprestimo emprestimo2 = criandoEmprestimo(2,2);

        LocalDate dataLocal = LocalDate.of(2023,9,20);  //definindo uma data do sistema aleatoria

        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal); //registrando os dois emprestimos
        obj.registroEmprestimo(emprestimo2,livrodao,usuariodao,emprestimodao,dataLocal);

        List<Livro> lista = objAdmUseCases.livrosMaisPopulares(livrodao);

        Assertions.assertEquals("Segredos do Universo",lista.get(0).getTitulo()); //verificando se o livro retornado é o que deve estar no topo da lista (mais emprestado)
        Assertions.assertEquals("Aventuras nas Montanhas",lista.get(1).getTitulo());
        //esses foram os unicos livros emprestados, logo sao os mais populares
    }

    /**
     * testando a contagem da quantidade de livros reservados
     * @throws UsuarioUseCasesExceptions excecoes do UsuarioUseCasesExceptions
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void qtdLivrosReservados() throws UsuarioUseCasesExceptions, DAOExceptions, IOException, ClassNotFoundException {

        criandoUserLivro(); //adicionando 2 livros e 2 usuarios
        criandoUserLivro();

        LocalDate dataLocal = LocalDate.of(2023,9,20);  //definindo uma data do sistema aleatoria

        objUsuarioUseCases.reservarLivros(1,1,dataLocal,livrodao,usuariodao);
        objUsuarioUseCases.reservarLivros(2,2,dataLocal,livrodao,usuariodao);

        Assertions.assertEquals(objAdmUseCases.qtdLivrosReservados(livrodao),2);
    }

    /**
     * testando a busca pelo hsitorico do usuario
     * @throws DAOExceptions excecoes do dao
     * @throws BlibUseCaseExceptions excecoes de BlibUseCaseExceptions
     */
    @Test
    void emprestimoUsuario() throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        criandoUserLivro(); //adicionando 2 livros e 2 usuarios
        LocalDate dataLocal = LocalDate.of(2023,9,11); //definindo uma data do sistema aleatoria
        Emprestimo emprestimo = criandoEmprestimo(1,1);
        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal);

        List<Emprestimo> lista = objAdmUseCases.emprestimoUsuario(1,usuariodao); //lista com os emprestimos do usuario
        Assertions.assertEquals(emprestimo,lista.get(0)); //verificando se o emprestimo é o mesmo que o emprestimo registrado no historico do usuario
    }

    /**
     * testando o bloqueio e desbloqueio do usuario
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void bloquearDesbloquearUsuario() throws DAOExceptions, IOException, ClassNotFoundException {

        criandoUserLivro(); //adicionando usuario
        Usuario usuario = usuariodao.findById(1);

        //bloqueando
        objAdmUseCases.bloquearDesbloquearUsuario(1,1,usuariodao);
        Assertions.assertEquals(true,usuariodao.findById(1).getBloqueio());

        //desbloqueando
        objAdmUseCases.bloquearDesbloquearUsuario(1,2,usuariodao);
        Assertions.assertEquals(false,usuariodao.findById(1).getBloqueio());

    }
}