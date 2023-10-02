package test.daotest;

import main.dao.operadores.OperadoresDAOmap;
import main.exceptions.dao.DAOExceptions;
import main.model.Livro;
import main.model.Operadores;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class OperadoresDAOmapTest {

    OperadoresDAOmap obj = new OperadoresDAOmap();
    //1 adm   2 biblio
    @BeforeEach
    public void setUp(){

        Operadores operadores = new Operadores("Rafael",1,"tosta123");
        Operadores operadores2 = new Operadores("Pamela",2,"cortez123");
        obj.create(operadores);
        obj.create(operadores2);

    }

    @AfterEach
    public void tearDown() throws DAOExceptions {
        obj.deleteMany();
    }

    @Test
    void create() throws DAOExceptions {
        Operadores operadores = new Operadores("Rafael",1,"tosta123");
        obj.create(operadores);

        Assertions.assertEquals(operadores,obj.findById(3)); //testando se o mesmo objeto foi registrado
    }

    @Test
    void delete() {
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

        Operadores operadores2 = new Operadores("Pamela",2,"cortez123");
        obj.update(operadores2,1);

        Assertions.assertEquals(operadores2,obj.findById(1)); //testando se o mesmo objeto foi atualizado
    }

    @Test
    void findMany() {

        Map<Integer,Operadores> map = obj.findMany();
        Assertions.assertNotNull(map);
    }

    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1));
    }
}