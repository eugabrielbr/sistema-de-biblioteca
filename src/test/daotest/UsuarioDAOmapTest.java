package test.daotest;


import main.dao.usuario.UsuarioDAOmap;

import main.exceptions.crud.DAOExceptions;
import main.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class UsuarioDAOmapTest {

    UsuarioDAOmap obj = new UsuarioDAOmap();
    public void addUsuario(){

        Usuario objUsuario = new Usuario("gabriel", "undefined", "00000000");
        obj.create(objUsuario);

    }
    @Test
    void create() throws DAOExceptions {

        addUsuario();
        Assertions.assertNotNull(obj.findById(1));

    }

    @Test
    void delete() {

        addUsuario();

        try{
            obj.delete(1);
        }
        catch (DAOExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void deleteMany() {

        addUsuario();
        obj.deleteMany();
        Assertions.assertTrue(obj.usuarioMap.isEmpty());
    }


    @Test
    void update() {

        addUsuario();

        Usuario objUsuario = new Usuario("gabri", "undefined", "00000000");

        try{
            obj.update(objUsuario,1);
        }
        catch (DAOExceptions e){
            Assertions.fail();
        }
    }

    @Test
    void findMany() {

        Map<Integer,Usuario> map = obj.findMany();
        Assertions.assertNotNull(map);

    }

    @Test
    void findById() throws DAOExceptions {

        addUsuario();
        Assertions.assertNotNull(obj.findById(1));

    }


}