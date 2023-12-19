package test.daotest;


import main.dao.DAO;
import main.dao.usuario.UsuarioDAO;
import main.dao.usuario.UsuarioDAOmap;

import main.exceptions.dao.DAOExceptions;
import main.model.Usuario;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Map;

class UsuarioDAOmapTest {
    /**
     * objeto da classe UsuarioDAOmap
     */
    UsuarioDAO obj = DAO.getUsuarioDAO();

    UsuarioDAOmapTest() throws IOException, ClassNotFoundException {
    }

    /**
     * condicao inicial antes dos testes
     */
    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException {

        Usuario objUsuario = new Usuario("gabriel", "undefined", "00000000");
        obj.create(objUsuario);


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
     * testando registro de usuario
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void create() throws DAOExceptions, IOException, ClassNotFoundException {

        Usuario usuario = new Usuario("gabriel", "undefined", "00000000");
        obj.create(usuario);

        Assertions.assertEquals(usuario,obj.findById(2)); //testando se o mesmo objeto foi registrado


    }
    /**
     * testando exclusão de objetos
     */
    @Test
    void delete() throws DAOExceptions, IOException, ClassNotFoundException {


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


        Usuario objUsuario = new Usuario("gabri", "undefined", "00000000");
        obj.update(objUsuario,1);

        Assertions.assertEquals(objUsuario,obj.findById(1));
    }
    /**
     * testando se a estrutura de dados é retornada
     */
    @Test
    void findMany() {

        Map<Integer,Usuario> map = obj.findMany();
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