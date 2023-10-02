package test.daotest;

import main.dao.livro.LivroDAOmap;
import main.exceptions.dao.DAOExceptions;
import main.model.Livro;
import main.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class LivroDAOmapTest {

    LivroDAOmap obj = new LivroDAOmap();

    @BeforeEach
    public void setUp(){

        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",null);
        obj.create(objLivro);

    }

    @AfterEach
    public void tearDown() throws DAOExceptions {
        obj.deleteMany();
    }

    @Test
    void findByAutor() throws DAOExceptions {


        List<Livro> var =  obj.findByAutor("albert einstein");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getAutor(),"albert einstein"); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);

    }

    @Test
    void findByTitulo() throws DAOExceptions {



        List<Livro> var =  obj.findByTitulo("teoria da relatividade");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getTitulo(),"teoria da relatividade"); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);

    }

    @Test
    void findByCategoria() throws DAOExceptions {



        List<Livro> var =  obj.findByCategoria("fisica");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getCategoria(),"fisica"); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);
    }

    @Test
    void findByISBN() throws DAOExceptions {



        List<Livro> var = obj.findByISBN(2222);
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getISBN(),2222); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);
    }

    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1));
    }

    @Test
    void create() throws DAOExceptions {


        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",null);
        obj.create(objLivro);

        Assertions.assertEquals(objLivro,obj.findById(2)); //testando se o mesmo objeto foi registrado

    }

    @Test
    void delete(){


        try{
            obj.delete(1);
        }
        catch (DAOExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void deleteMany() throws DAOExceptions {


        obj.deleteMany();
        Assertions.assertTrue(obj.findMany().isEmpty());

    }

    @Test
    void update() throws DAOExceptions {



        Livro objLivro = new Livro("teoria da relat","albert einstein",null,2222,"fisica",null);
        obj.update(objLivro,1);

        Assertions.assertEquals(objLivro,obj.findById(1)); //testando se o mesmo objeto foi atualizado

    }

    @Test
    void findMany() {

        Map<Integer,Livro> map = obj.findMany();
        Assertions.assertNotNull(map);

    }

}