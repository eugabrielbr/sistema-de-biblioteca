package test.modeltest.functest;

import main.dao.emprestimo.EmprestimoDAOmap;
import main.dao.livro.LivroDAOmap;
import main.dao.usuario.UsuarioDAOmap;
import main.exceptions.crud.DAOExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;
import main.model.func.BlibUseCases;
import main.model.func.UsuarioUseCases;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BlibUseCasesTest {

    LivroDAOmap livrodao = new LivroDAOmap();
    UsuarioDAOmap usuarioao = new UsuarioDAOmap();
    BlibUseCases obj = new BlibUseCases();
    EmprestimoDAOmap emprestimodao = new EmprestimoDAOmap();
    UsuarioUseCases objUsuarioUseCases = new UsuarioUseCases();



    public Emprestimo criandoEmprestimo(Integer idUsuario, Integer idlivro) throws DAOExceptions {


        Usuario user = usuarioao.findById(idUsuario);
        Livro livro = livrodao.findById(idlivro);
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        //user.setBloqueio(true); //bloqueio do admin
        return emprestimo;

    }

    public void criandoUserLivro(){

        Usuario user = new Usuario("gabriel", "undefined", "00000000");
        Livro livro = new Livro("teoria da relativida","albert einstein",null,2222,"fisica",null);
        livrodao.create(livro);
        usuarioao.create(user);
    }
    @Test
    void registroEmprestimo() throws DAOExceptions {


        //******************TESTE DE EMPRESTIMO USUARIO SEM PENDENCIAS*****************************

        criandoUserLivro(); //registrando usuario e livro
        Emprestimo emprestimo = criandoEmprestimo(1,1); // criando emprestimo
        LocalDate dataLocal = LocalDate.of(2023,9,20); //simulando data do sistema para testes (sem multa)
        obj.registroEmprestimo(emprestimo,livrodao,usuarioao,emprestimodao,dataLocal);//registrando emprestimo


        Assertions.assertNotNull(emprestimodao.findById(1)); //buscando para ver se o emprestimo foi criado

        LocalDate testeDataDevolucao = emprestimodao.findById(1).getDataDevolucao(); //verificando se a data de devolucao esta correta
        Assertions.assertEquals(LocalDate.of(2023,9,21),testeDataDevolucao);

        usuarioao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        criandoUserLivro(); //registrando usuario e livro
        Emprestimo emprestimox = criandoEmprestimo(1,1); // criando emprestimo

        //***************TESTE DE EMPRESTIMO COM USUARIO COM PENDENCIAS**********************

        //TESTANDO EMPRESTIMO COM MULTA
        LocalDate dataLocalMulta = LocalDate.of(2023,9,25); //data do sistema para gerar atraso de devolucao
        obj.registroEmprestimo(emprestimox,livrodao,usuarioao,emprestimodao,dataLocalMulta); //registrando primeiro emprestimo
        obj.registroEmprestimo(emprestimox,livrodao,usuarioao,emprestimodao,dataLocalMulta); //tentando registrar o segundo emprestimo mesmo com pendencia


        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(2);  //se excecao de nao encontrado é lancada, significa que o segundo emprestimo nao foi registrado
        });

        usuarioao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //TESTANDO SE O USUARIO CONSEGUE REALIZAR EMPRESTIMO MESMO BLOQUEADO PELO ADM

        criandoUserLivro(); //registrando usuario e livro
        Emprestimo emprestimot = criandoEmprestimo(1,1); // criando emprestimo

        Usuario usuario = usuarioao.findById(1);
        usuario.setBloqueio(true); //bloqueando usuario
        usuarioao.update(usuario,1); //att dados

        obj.registroEmprestimo(emprestimox,livrodao,usuarioao,emprestimodao,dataLocal); // tentando registrar emprestimo com usuario bloqueado

        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(1);  //se excecao de nao encontrado é lancada, significa que o segundo emprestimo nao foi registrado pq o usuario esta bloqueado
        });
        //System.out.println(usuarioao.findById(1)); // por esse print da pra ver q o usuario esta sem multa, porem foi bloqueado, por isso nao conseguiu fazer o emprestimo

        usuarioao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //TESTANDO FAZER EMPRESTIMO COM LIVRO INDISPONIVEL

        criandoUserLivro(); //registrando usuario
        criandoUserLivro(); //registrando usuario2
        Emprestimo emprestimo1 = criandoEmprestimo(1,1); //usuario 1
        Emprestimo emprestimo2 = criandoEmprestimo(2,1); //usuario 2 tentando pegar o livro de ID 1

        obj.registroEmprestimo(emprestimo1,livrodao,usuarioao,emprestimodao,dataLocal); //registro emprestimo usuario1
        obj.registroEmprestimo(emprestimo2,livrodao,usuarioao,emprestimodao,dataLocal); //tentativa de emprestimo do usuario2


        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(2);  //se excecao de nao encontrado é lancada, significa que o segundo emprestimo nao foi registrado devido ao livro esta indisponivel
        });


        usuarioao.deleteMany();
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

        obj.registroEmprestimo(emprestimoa,livrodao,usuarioao,emprestimodao,dataLocal); //emprestimo 1
        obj.registroEmprestimo(emprestimob,livrodao,usuarioao,emprestimodao,dataLocal); //emprestimo 2
        obj.registroEmprestimo(emprestimoc,livrodao,usuarioao,emprestimodao,dataLocal); //emprestimo 3
        obj.registroEmprestimo(emprestimod,livrodao,usuarioao,emprestimodao,dataLocal); //emprestimo de ID 4 que deve dar erro

        Assertions.assertEquals(3,usuarioao.findById(1).getQtdEmprestimos()); //se tiver 3 emprestimos, significa q o 4 nao foi bem sucedido
        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(4);  //confirmando que realmente o 4 emprestimo nao ocorreu
        });

        Assertions.assertNotNull(emprestimodao.findById(3)); //verificando se os outros foram registrados
        Assertions.assertNotNull(emprestimodao.findById(2));
        Assertions.assertNotNull(emprestimodao.findById(1));


        usuarioao.deleteMany();
        livrodao.deleteMany(); //dando clear para novos testes
        emprestimodao.deleteMany();

        //****************TESTANDO EMPRESTIMOS COM SITUACOES DE RESERVA*******************

        criandoUserLivro(); //registrando usuario e livro
        criandoUserLivro(); //registrando usuario e livro
        criandoUserLivro(); //registrando usuario e livro
        LocalDate dataLocalReserva = LocalDate.of(2023,9,18); //data do sistema que nao causa multa


        //TESTANDO EMPRESTIMO FEITO POR USUARIO QUE NAO ESTAVA NA RESERVA


        objUsuarioUseCases.reservarLivros(1,2,dataLocalReserva,livrodao,usuarioao); //efetuando reserva
        Emprestimo emprestimoy = criandoEmprestimo(1,1); // criando emprestimo para usuario de ID 1, mesmo com o livro emprestado para usuario de ID 2
        obj.registroEmprestimo(emprestimoy,livrodao,usuarioao,emprestimodao,dataLocalReserva); //tentando fazer emprestimo com livro ja reservado

        assertThrows(DAOExceptions.class, () -> {
            emprestimodao.findById(1);  //se excecao de nao encontrado é lancada, significa que o emprestimo nao foi registrado devido reserva
        });


        //TESTANDO EMPRESTIMO COM USUARIO RESERVA IGUAL AO USUARIO QUE ESTA SOLICITANDO EMPRESTIMO

        Emprestimo emprestimoz = criandoEmprestimo(2,1); //lembrando que o usuario de ID 2 que ta com prioridade na reserva
        obj.registroEmprestimo(emprestimoz,livrodao,usuarioao,emprestimodao,dataLocalReserva); //tentando fazer o emprestimo para o usuario que reservou
        Assertions.assertNotNull(emprestimodao.findById(1));


            
    }



    @Test
    void registroDevolucao() throws DAOExceptions {

        Emprestimo emprestimo = criandoEmprestimo(1,1);
        LocalDate dataLocal = LocalDate.of(2023,9,20); //simulando data do sistema (sem multa)
        obj.registroEmprestimo(emprestimo,livrodao,usuarioao,emprestimodao,dataLocal);//registrando emprestimo

        obj.registroDevolucao(1,1,emprestimodao,livrodao,usuarioao,dataLocal);
        assertThrows(DAOExceptions.class, () -> { //verificando se o emprestimo foi removido do banco de dados (concluido)
            emprestimodao.findById(1);  //se excecao de nao encontrado é lancada, significa que nada foi registrado
        });

        //terminar
        //status livro
        //historico devolucao usuario


    }
 
    @Test
    void atualizarMulta() throws DAOExceptions {

        Emprestimo test = criandoEmprestimo(1,1);
        BlibUseCases objReg = new BlibUseCases();
       // objReg.registroEmprestimo(test,livrodao,usuarioao,emprestimodao);

        //try {
            //obj.atualizarMulta(1,emprestimodao,usuarioao);
       // }
        //catch (DAOExceptions e){
        //    Assertions.fail();
        //}

    }
}