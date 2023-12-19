package test.modeltest.functest;

import main.dao.DAO;
import main.dao.emprestimo.EmprestimoDAO;
import main.dao.emprestimo.EmprestimoDAOmap;
import main.dao.livro.LivroDAO;
import main.dao.livro.LivroDAOmap;
import main.dao.usuario.UsuarioDAO;
import main.dao.usuario.UsuarioDAOmap;
import main.exceptions.usecases.BlibUseCaseExceptions;
import main.exceptions.dao.DAOExceptions;
import main.exceptions.usecases.UsuarioUseCasesExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;
import main.model.func.BlibUseCases;
import main.model.func.UsuarioUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BlibUseCasesTest {

    LivroDAO livrodao = DAO.getLivroDAO();  //SE DER MERDA VOLTAR PARA LIVRODAOMAP
    UsuarioDAO usuariodao = DAO.getUsuarioDAO();
    BlibUseCases obj = new BlibUseCases();
    EmprestimoDAO emprestimodao = DAO.getEmprestimoDAO();
    UsuarioUseCases objUsuarioUseCases = new UsuarioUseCases();

    BlibUseCasesTest() throws IOException, ClassNotFoundException {
    }


    public Emprestimo criandoEmprestimo(Integer idUsuario, Integer idlivro) throws DAOExceptions {


        Usuario user = usuariodao.findById(idUsuario);
        Livro livro = livrodao.findById(idlivro);
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        return emprestimo;

    }

    @AfterEach
    public void tearDown() throws DAOExceptions, IOException, ClassNotFoundException {

        livrodao.deleteMany();
        usuariodao.deleteMany();
        emprestimodao.deleteMany();

    }

    public void criandoUserLivro() throws IOException, ClassNotFoundException {

        Usuario user = new Usuario("gabriel", "undefined", "00000000");
        Livro livro = new Livro("teoria da relativida","albert einstein",null,2222,"fisica",null);
        livrodao.create(livro);
        usuariodao.create(user);
    }
    @Test
    void registroEmprestimo() throws DAOExceptions, BlibUseCaseExceptions, UsuarioUseCasesExceptions, IOException, ClassNotFoundException {


        //******************TESTE DE EMPRESTIMO USUARIO SEM PENDENCIAS*****************************

        criandoUserLivro(); //registrando usuario e livro
        Emprestimo emprestimo = criandoEmprestimo(1,1); // criando emprestimo
        LocalDate dataLocal = LocalDate.of(2023,9,20); //simulando data do sistema para testes (sem multa)

        try {
            obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal);//registrando emprestimo
        }
        catch (BlibUseCaseExceptions e){
            Assertions.fail();
        }


        LocalDate testeDataDevolucao = emprestimodao.findById(1).getDataDevolucao(); //verificando se a data de devolucao esta correta
        Assertions.assertEquals(LocalDate.of(2023,9,21),testeDataDevolucao);

        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        criandoUserLivro(); //registrando usuario e livro
        criandoUserLivro();
        Emprestimo emprestimox = criandoEmprestimo(1,1); // criando emprestimo
        Emprestimo emprestimok = criandoEmprestimo(1,2);


        //***************TESTE DE EMPRESTIMO COM USUARIO COM PENDENCIAS**********************

        //TESTANDO EMPRESTIMO COM MULTA

        LocalDate dataLocalMulta = LocalDate.of(2023,9,25); //data do sistema para gerar atraso de devolucao
        obj.registroEmprestimo(emprestimox,livrodao,usuariodao,emprestimodao,dataLocalMulta); //registrando primeiro emprestimo

        try {
            obj.registroEmprestimo(emprestimok,livrodao,usuariodao,emprestimodao,dataLocalMulta); //tentando registrar o segundo emprestimo mesmo com pendencia
        }
        catch (BlibUseCaseExceptions e){
            Assertions.assertEquals(BlibUseCaseExceptions.MULTADO + " - ID: " + emprestimox.getUsuario().getID(),e.getMessage()); //vericando excecao
        }


        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //TESTANDO SE O USUARIO CONSEGUE REALIZAR EMPRESTIMO MESMO BLOQUEADO PELO ADM

        criandoUserLivro(); //registrando usuario e livro
        Emprestimo emprestimot = criandoEmprestimo(1,1); // criando emprestimo

        Usuario usuario = usuariodao.findById(1);
        usuario.setBloqueio(true); //bloqueando usuario
        usuariodao.update(usuario,1); //att dados

        try {
            obj.registroEmprestimo(emprestimot, livrodao, usuariodao, emprestimodao, dataLocal); // tentando registrar emprestimo com usuario bloqueado
        }
        catch (BlibUseCaseExceptions e){

            Assertions.assertEquals(BlibUseCaseExceptions.BLOQUEADO + " - ID: " + emprestimox.getUsuario().getID(),e.getMessage()); //verificando excecao
        }

        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //TESTANDO FAZER EMPRESTIMO COM LIVRO INDISPONIVEL

        criandoUserLivro(); //registrando usuario
        criandoUserLivro(); //registrando usuario2
        Emprestimo emprestimo1 = criandoEmprestimo(1,1); //usuario 1
        Emprestimo emprestimo2 = criandoEmprestimo(2,1); //usuario 2 tentando pegar o livro de ID 1

        obj.registroEmprestimo(emprestimo1,livrodao,usuariodao,emprestimodao,dataLocal); //registro emprestimo usuario1

        try {
            obj.registroEmprestimo(emprestimo2, livrodao, usuariodao, emprestimodao, dataLocal); //tentativa de emprestimo do usuario2
        }catch (BlibUseCaseExceptions e){
            Assertions.assertEquals(BlibUseCaseExceptions.INDISPONIVEL + " - ID: " + emprestimo1.getUsuario().getID(),e.getMessage());
        }

        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(2);  //se excecao de nao encontrado é lancada, significa que o segundo emprestimo nao foi registrado devido ao livro esta indisponivel
        });


        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //TESTANDO USUARIO TENTANDO FAZER MAIS DE 3 EMPRESTIMOS

        criandoUserLivro(); //registrando new usuario e livro
        criandoUserLivro(); //registrando new usuario e livro
        criandoUserLivro(); //registrando new usuario e livro
        criandoUserLivro(); //registrando new usuario e livro

        Emprestimo emprestimoa = criandoEmprestimo(1,1);
        Emprestimo emprestimob = criandoEmprestimo(1,2);
        Emprestimo emprestimoc = criandoEmprestimo(1,3);
        Emprestimo emprestimod = criandoEmprestimo(1,4);

        obj.registroEmprestimo(emprestimoa, livrodao, usuariodao, emprestimodao, dataLocal); //emprestimo 1
        obj.registroEmprestimo(emprestimob, livrodao, usuariodao, emprestimodao, dataLocal); //emprestimo 2
        obj.registroEmprestimo(emprestimoc, livrodao, usuariodao, emprestimodao, dataLocal); //emprestimo 3

        try {

            obj.registroEmprestimo(emprestimod, livrodao, usuariodao, emprestimodao, dataLocal); //tentativa do 4 emprestimo

        }catch (BlibUseCaseExceptions e){

            Assertions.assertEquals(BlibUseCaseExceptions.ATIVOS + " - ID: " + emprestimod.getUsuario().getID(),e.getMessage());
        }

        Assertions.assertEquals(3,usuariodao.findById(1).getQtdEmprestimos()); //se tiver 3 emprestimos, significa q o 4 nao foi bem sucedido


        Assertions.assertNotNull(emprestimodao.findById(3)); //verificando se os outros foram registrados
        Assertions.assertNotNull(emprestimodao.findById(2));
        Assertions.assertNotNull(emprestimodao.findById(1));


        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //****************TESTANDO EMPRESTIMOS COM SITUACOES DE RESERVA*******************

        criandoUserLivro(); //registrando usuario e livro
        criandoUserLivro(); //registrando usuario e livro
        criandoUserLivro(); //registrando usuario e livro
        LocalDate dataLocalReserva = LocalDate.of(2023,9,18); //data do sistema que nao causa multa


        //TESTANDO EMPRESTIMO FEITO POR USUARIO QUE NAO ESTAVA NA RESERVA


        objUsuarioUseCases.reservarLivros(1,2,dataLocalReserva,livrodao,usuariodao); //efetuando reserva
        Emprestimo emprestimoy = criandoEmprestimo(1,1); // criando emprestimo para usuario de ID 1, mesmo com o livro emprestado para usuario de ID 2

        try {
            obj.registroEmprestimo(emprestimoy, livrodao, usuariodao, emprestimodao, dataLocalReserva); //tentando fazer emprestimo com livro ja reservado
        }catch (BlibUseCaseExceptions e){
            Assertions.assertEquals(BlibUseCaseExceptions.RESERVADO + " - ID: " + emprestimoy.getUsuario().getID(),e.getMessage());
        }



        //TESTANDO EMPRESTIMO COM USUARIO RESERVA IGUAL AO USUARIO QUE ESTA SOLICITANDO EMPRESTIMO

        Emprestimo emprestimoz = criandoEmprestimo(2,1); //lembrando que o usuario de ID 2 que ta com prioridade na reserva
        obj.registroEmprestimo(emprestimoz,livrodao,usuariodao,emprestimodao,dataLocalReserva); //tentando fazer o emprestimo para o usuario que reservou
        Assertions.assertNotNull(emprestimodao.findById(1));

            
    }



    @Test
    void registroDevolucao() throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        //DEVOLUCAO NORMAL
        criandoUserLivro();
        Emprestimo emprestimo = criandoEmprestimo(1,1);
        LocalDate dataLocal = LocalDate.of(2023,9,20); //simulando data do sistema (sem multa)
        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocal);//registrando emprestimo

        dataLocal = LocalDate.of(2023,9,21);

        try {
            obj.registroDevolucao(1, 1, emprestimodao, livrodao, usuariodao, dataLocal); //efetuando devolucao
        }catch(BlibUseCaseExceptions e) {
            Assertions.fail();
        }

        //TESTANDO EXCECAO CASO NAO CONSIGA REALIZAR DEVOLUCAO

        try {
            obj.registroDevolucao(1, 1, emprestimodao, livrodao, usuariodao, dataLocal); //tentando devolver algo que ja foi devolvido
        }catch (BlibUseCaseExceptions e){
            Assertions.assertEquals(BlibUseCaseExceptions.FAILDEVOLUCAO + " - ID: " + emprestimo.getLivro().getID(),e.getMessage()); //verificando se a excecao correta é lancada
        }


    }
 
    @Test
    void atualizarMulta() throws DAOExceptions, BlibUseCaseExceptions, IOException, ClassNotFoundException {

        criandoUserLivro();
        criandoUserLivro();//vou criar outro livro, para a excecao de livro indiponivel nao interferir nos testes
        Emprestimo emprestimo = criandoEmprestimo(1,1); //criando emprestimo
        Emprestimo emprestimo2 = criandoEmprestimo(1,2);
        LocalDate dataLocalMulta = LocalDate.of(2023,9,22); //data do sistema para gerar atraso de devolucao

        obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,dataLocalMulta);//realizando primeiro emprestimo

        try {
            obj.registroEmprestimo(emprestimo2, livrodao, usuariodao, emprestimodao, dataLocalMulta); //realizando o segundo emprestimo que ira gerar a multa
        }catch (BlibUseCaseExceptions e){
            Assertions.assertEquals(BlibUseCaseExceptions.MULTADO + " - ID: " + emprestimo2.getUsuario().getID(),e.getMessage()); //verificando se a excecao esta correta
        }

        Assertions.assertEquals(LocalDate.of(2023,9,23),usuariodao.findById(1).getDataDaMulta());
        //o emprestimo tem data de devolucao para o dia 21, mas o usuario atrasou um dia, logo recebeu multa de dois dias (21+2=23)
        //lembrando que a data de emprestimo consta no metodo criandoEmprestimo()

    }

    @Test
    void atualizarReserva() throws DAOExceptions, BlibUseCaseExceptions, UsuarioUseCasesExceptions, IOException, ClassNotFoundException {

        //TESTANDO USUARIO INDO PEGAR O LIVRO QUE RESERVOU FORA DO PRAZO
        criandoUserLivro();
        criandoUserLivro(); //registrando mais um usuario, pq se a fila tiver vazia o outro vai pode pegar msm fora de prazo
        LocalDate dataLocalReserva = LocalDate.of(2023,9,3); //data da reserva

        objUsuarioUseCases.reservarLivros(livrodao.findById(1).getID(),usuariodao.findById(1).getID(),dataLocalReserva,livrodao,usuariodao);//efetuando reserva de livro, o usuario tem ate 7 dias para pegar
        objUsuarioUseCases.reservarLivros(livrodao.findById(1).getID(),usuariodao.findById(2).getID(),dataLocalReserva,livrodao,usuariodao);//reservando o livro para outro usuario


        Emprestimo emprestimo = criandoEmprestimo(1,1);

        try {
            obj.registroEmprestimo(emprestimo,livrodao,usuariodao,emprestimodao,emprestimo.getDataEmprestimo());//tentando pegar o livro emprestado fora do prazo de reserva
        }catch (BlibUseCaseExceptions e){
            Assertions.assertEquals(BlibUseCaseExceptions.RESERVADO + " - ID: " + emprestimo.getUsuario().getID(),e.getMessage());
            //devido ao usuario1 ir pegar o livro fora de prazo, a prioridade agora é do usuario2, por isso a excecao de reservado
        }

        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(1);  //se excecao de nao encontrado é lancada, significa que o emprestimo nao foi registrado devido ao prazo de reserva ter excedido
        });

        Assertions.assertEquals(LocalDate.of(2023,9,18),livrodao.findById(1).getDataReserva());//verificando se o prazo de 7 dias do proximo usuario com prioridade foi atualizada
        //data que o usuario tentou pegar emprestimo (11) + 7 dias de prazo para novo usuario reserva (11+7) = 18

        //TESTANDO SE A DATA DE RESERVA VAI SER ATUALIZADA A PARTIR DE UMA DEVOLUCAO (DISPONIBILIDADE DE UM LIVRO)

        usuariodao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        criandoUserLivro(); //usuario 1 que vai realizar emprestimo
        criandoUserLivro(); //usuario 2 que vai realizar reserva

        Emprestimo emprestimo1 = criandoEmprestimo(1,1);
        LocalDate dataLocalSemMulta = LocalDate.of(2023,9,18);

        obj.registroEmprestimo(emprestimo1,livrodao,usuariodao,emprestimodao,dataLocalSemMulta); //realizando emprestimo para o usuario 1
        objUsuarioUseCases.reservarLivros(1,2,dataLocalSemMulta,livrodao,usuariodao); //realizando reserva do livro que foi emprestado

        obj.registroDevolucao(1,1,emprestimodao,livrodao,usuariodao,dataLocalSemMulta);//devolvendo o livro

        Assertions.assertEquals(LocalDate.of(2023,9,25),livrodao.findById(1).getDataReserva());
        //data de devolucao (dia 18, onde o livro ficou disponivel) + 7 = data da proxima reserva (25)

    }
}