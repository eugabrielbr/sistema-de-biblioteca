package test.daotest;

import main.dao.emprestimo.EmprestimoDAOmap;
import main.dao.livro.LivroDAOmap;
import main.exceptions.dao.DAOExceptions;
import main.model.Emprestimo;
import main.model.Livro;
import main.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

class EmprestimoDAOmapTest {

    EmprestimoDAOmap obj = new EmprestimoDAOmap();

    @BeforeEach
    public void setUp(){

        Usuario user = new Usuario("Gabriel","Rua das flores","87654327");
        Livro livro = new Livro("Segredos do Universo", "Sarah Johnson", "Editora Imaginação", 1234, "Aventura", "Estante 1");
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        obj.create(emprestimo);

    }

    @AfterEach
    public void tearDown() throws DAOExceptions {
        obj.deleteMany();
    }
    @Test
    void create() throws DAOExceptions {

        Usuario user = new Usuario("Gabriel","Rua das flores","87654327");
        Livro livro = new Livro("Segredos do Universo", "Sarah Johnson", "Editora Imaginação", 1234, "Aventura", "Estante 1");
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        obj.create(emprestimo);

        Assertions.assertEquals(emprestimo,obj.findById(2)); //testando se o mesmo objeto foi registrado

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

        Usuario user = new Usuario("Gabriel","Rua do fogo","87654327");
        Livro livro = new Livro("Segredos do Universo", "Sarah Jackson", "Editora Imaginario", 4321, "Aventura", "Estante 1");
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);

        obj.update(emprestimo,1);

        Assertions.assertEquals(emprestimo,obj.findById(1)); //testando se o mesmo objeto foi atualizado
    }

    @Test
    void findMany() {

        Map<Integer,Emprestimo> map = obj.findMany();
        Assertions.assertNotNull(map);
    }

    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1));
    }
}