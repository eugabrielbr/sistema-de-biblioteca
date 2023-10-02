package test.daotest;


import main.dao.usuario.UsuarioDAOmap;

import main.exceptions.dao.DAOExceptions;
import main.model.Usuario;
import org.junit.jupiter.api.*;

import java.util.Map;

class UsuarioDAOmapTest {

    UsuarioDAOmap obj = new UsuarioDAOmap();


    @BeforeEach
    public void setUp(){

        Usuario objUsuario = new Usuario("gabriel", "undefined", "00000000");
        obj.create(objUsuario);


    }

    @AfterEach
    public void tearDown(){
        obj.deleteMany();
    }

    @Test
    void create() throws DAOExceptions {

        Usuario usuario = new Usuario("gabriel", "undefined", "00000000");
        obj.create(usuario);

        Assertions.assertEquals(usuario,obj.findById(2)); //testando se o mesmo objeto foi registrado


    }

    @Test
    void delete() throws DAOExceptions {


        try{
            obj.delete(1);
        }
        catch (DAOExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void deleteMany() {

        obj.deleteMany();
        Assertions.assertTrue(obj.findMany().isEmpty());
    }


    @Test
    void update() throws DAOExceptions {


        Usuario objUsuario = new Usuario("gabri", "undefined", "00000000");
        obj.update(objUsuario,1);

        Assertions.assertEquals(objUsuario,obj.findById(1));
    }

    @Test
    void findMany() {

        Map<Integer,Usuario> map = obj.findMany();
        Assertions.assertNotNull(map);

    }

    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1));

    }


}