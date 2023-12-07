package test.daotest;

import main.dao.DAO;
import main.dao.livro.LivroDAO;
import main.dao.livro.LivroDAOmap;
import main.exceptions.dao.DAOExceptions;
import main.model.Livro;
import main.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class LivroDAOmapTest {

    /**
     * objeto da Classe LivroDAOmap
     */
    LivroDAO obj = DAO.getLivroDAO();

    LivroDAOmapTest() throws IOException, ClassNotFoundException {
    }

    /**
     * condicao inicial antes dos testes
     */
    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException {

        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",null);
        obj.create(objLivro);

    }
    /**
     * condicao para o final de cada teste
     * @throws DAOExceptions excecoes do dao
     */
    @AfterEach
    public void tearDown() throws DAOExceptions, IOException, ClassNotFoundException {
        obj.deleteMany();
    }

    /**
     * testando pesquisa por autor
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findByAutor() throws DAOExceptions {


        List<Livro> var =  obj.findByAutor("albert einstein");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getAutor(),"albert einstein"); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);

    }
    /**
     * testando pesquisa por titulo
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findByTitulo() throws DAOExceptions {

        List<Livro> var =  obj.findByTitulo("teoria da relatividade");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getTitulo(),"teoria da relatividade"); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);

    }
    /**
     * testando pesquisa por categoria
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findByCategoria() throws DAOExceptions {



        List<Livro> var =  obj.findByCategoria("fisica");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getCategoria(),"fisica"); //verificando se o livro retornado realmente tem a info solicitada

        //System.out.println(var);
    }
    /**
     * testando pesquisa por ISBN
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findByISBN() throws DAOExceptions {


        List<Livro> var = obj.findByISBN(2222);
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertEquals(var.get(0).getISBN(),2222); //verificando se o livro retornado realmente tem a info solicitada


    }
    /**
     * testando pesquisa por ID
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1));
    }
    /**
     * testando registro de livro
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void create() throws DAOExceptions, IOException, ClassNotFoundException {


        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",null);
        obj.create(objLivro);

        Assertions.assertEquals(objLivro,obj.findById(2)); //testando se o mesmo objeto foi registrado

    }
    /**
     * testando exclusão de objetos
     */
    @Test
    void delete() throws IOException, ClassNotFoundException {


        try{
            obj.delete(1);
        }
        catch (DAOExceptions e){
            Assertions.fail();
        }

    }
    /**
     * testando exclusao de todos os dados
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException {


        obj.deleteMany();
        Assertions.assertTrue(obj.findMany().isEmpty());

    }
    /**
     * testando a atualizacao de objetos
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void update() throws DAOExceptions, IOException, ClassNotFoundException {



        Livro objLivro = new Livro("teoria da relat","albert einstein",null,2222,"fisica",null);
        obj.update(objLivro,1);

        Assertions.assertEquals(objLivro,obj.findById(1)); //testando se o mesmo objeto foi atualizado

    }
    /**
     * testando se a estrutura de dados é retornada
     */
    @Test
    void findMany() {

        Map<Integer,Livro> map = obj.findMany();
        Assertions.assertNotNull(map);

    }

}