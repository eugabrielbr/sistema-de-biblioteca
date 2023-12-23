package test.java.com.example.sistemadebiblioteca.daotest;

import main.java.com.example.sistemadebiblioteca.dao.DAO;
import main.java.com.example.sistemadebiblioteca.dao.SaveAndLoad;
import main.java.com.example.sistemadebiblioteca.dao.operadores.OperadoresDAO;
import main.java.com.example.sistemadebiblioteca.dao.operadores.OperadoresDAOmap;
import main.java.com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import main.java.com.example.sistemadebiblioteca.model.Livro;
import main.java.com.example.sistemadebiblioteca.model.Operadores;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class OperadoresDAOmapTest {
    /**
     * objeto da classe OperadoresDAOmap
     */
    OperadoresDAO obj = DAO.getOperadoresDAO();

    OperadoresDAOmapTest() throws IOException, ClassNotFoundException {
    }
    //1 adm   2 biblio
    /**
     * condicao inicial antes dos testes
     */
    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException {

        Operadores operadores = new Operadores("Rafael",1,"tosta123");
        Operadores operadores2 = new Operadores("Pamela",2,"cortez123");
        obj.create(operadores);
        obj.create(operadores2);

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
     * testando registro de operadores
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void create() throws DAOExceptions, IOException, ClassNotFoundException {
        Operadores operadores = new Operadores("Rafael",1,"tosta123");
        obj.create(operadores);

        Assertions.assertEquals(operadores,obj.findById(3)); //testando se o mesmo objeto foi registrado
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

        Operadores operadores2 = new Operadores("Pamela",2,"cortez123");
        obj.update(operadores2,1);

        SaveAndLoad<Operadores> test = new SaveAndLoad<>("operadores.bin");

        Assertions.assertEquals(operadores2,obj.findById(1)); //testando se o mesmo objeto foi atualizado
    }
    /**
     * testando se a estrutura de dados é retornada
     */
    @Test
    void findMany() {

        Map<Integer,Operadores> map = obj.findMany();
        Assertions.assertNotNull(map);
    }
    /**
     * testando pesquisa por ID
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1));
    }
}