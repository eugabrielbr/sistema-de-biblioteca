package daotest;

import com.example.sistemadebiblioteca.dao.DAO;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAO;
import com.example.sistemadebiblioteca.dao.emprestimo.EmprestimoDAOmap;
import com.example.sistemadebiblioteca.dao.livro.LivroDAOmap;
import com.example.sistemadebiblioteca.exceptions.dao.DAOExceptions;
import com.example.sistemadebiblioteca.model.Emprestimo;
import com.example.sistemadebiblioteca.model.Livro;
import com.example.sistemadebiblioteca.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

class EmprestimoDAOmapTest {

    /**
     * objeto da Classe EmprestimoDAOmap
     */
    EmprestimoDAO obj = DAO.getEmprestimoDAO();

    EmprestimoDAOmapTest() throws IOException, ClassNotFoundException {
    }


    /**
     * condicao inicial antes dos testes
     */
    @BeforeEach
    public void setUp() throws IOException, ClassNotFoundException, DAOExceptions {

        Usuario user = new Usuario("Gabriel","Rua das flores","87654327");
        Livro livro = new Livro("Segredos do Universo", "Sarah Johnson", "Editora Imaginação", 1234, "Aventura", "Estante 1");
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        obj.create(emprestimo);

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
     * testando registro de emprestimo
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void create() throws DAOExceptions, IOException, ClassNotFoundException {

        Usuario user = new Usuario("Gabriel","Rua das flores","87654327");
        Livro livro = new Livro("Segredos do Universo", "Sarah Johnson", "Editora Imaginação", 1234, "Aventura", "Estante 1");
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);
        obj.create(emprestimo);

        Assertions.assertEquals(emprestimo,obj.findById(2)); //testando se o objeto emprestimo é o mesmo que o da busca

    }

    /**
     * testando exclusão de objetos
     */
    @Test
    void delete() throws IOException, ClassNotFoundException {


        try{
            obj.delete(1); //tentando deletar
        }
        catch (DAOExceptions e){
            Assertions.fail(); //uma excecao de falha é lançada caso nao tenha sucesso na exclusao
        }
    }

    /**
     * testando exclusao de todos os dados
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void deleteMany() throws DAOExceptions, IOException, ClassNotFoundException {

        obj.deleteMany();
        Assertions.assertTrue(obj.findMany().isEmpty()); //verificando se a estrutura de dados esta vazia
    }

    /**
     * testando a atualizacao de objetos
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void update() throws DAOExceptions, IOException, ClassNotFoundException {

        Usuario user = new Usuario("Gabriel","Rua do fogo","87654327");
        Livro livro = new Livro("Segredos do Universo", "Sarah Jackson", "Editora Imaginario", 4321, "Aventura", "Estante 1");
        LocalDate data1 = LocalDate.of(2023,9,11);
        Emprestimo emprestimo = new Emprestimo(user,data1,livro);

        obj.update(emprestimo,1); //fazendo o update do emprestimo ja registrado com um novo

        Assertions.assertEquals(emprestimo,obj.findById(1)); //testando se o mesmo objeto foi atualizado
    }

    /**
     * testando se a estrutura de dados é retornada
     */
    @Test
    void findMany() {

        Map<Integer,Emprestimo> map = obj.findMany();
        Assertions.assertNotNull(map); //verificando se algo foi retornado
    }

    /**
     * testando busca por id
     * @throws DAOExceptions excecoes do dao
     */
    @Test
    void findById() throws DAOExceptions {

        Assertions.assertNotNull(obj.findById(1)); //verificando se algo e retornado
    }
}