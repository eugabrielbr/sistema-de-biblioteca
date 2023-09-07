package test.DAO;

import DAO.livro.LivroDAOmap;
import model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class LivroDAOmapTest {

    LivroDAOmap obj = new LivroDAOmap();
    public void addLivro(){

        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",true,null);
        obj.create(objLivro);


    }
    @Test
    void findByAutor() {

        addLivro();

        List<Livro> var =  obj.findByAutor("albert einstein");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);

    }

    @Test
    void findByTitulo() {

        addLivro();

        List<Livro> var =  obj.findByTitulo("teoria da relatividade");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);

    }

    @Test
    void findByCategoria() {

        addLivro();

        List<Livro> var =  obj.findByCategoria("fisica");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);
    }

    @Test
    void findByISBN() {

        addLivro();

        List<Livro> var = obj.findByISBN(2222);
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);
    }

    @Test
    void findById() {

        addLivro();
        Assertions.assertNotNull(obj.findById(1));
    }

    @Test
    void create() {

        addLivro();
        Assertions.assertNotNull(obj.findById(1));

    }

    @Test
    void delete() {

        addLivro();
        Assertions.assertTrue(obj.delete(1));

    }

    @Test
    void deleteMany() {

        List <Integer> lista = new ArrayList<>();

        for (int i = 1; i <= 3; i++){
            addLivro();
            lista.add(i);
        }

        obj.deleteMany(lista);

        Assertions.assertTrue(obj.acervo.isEmpty());
    }

    @Test
    void update() {
    }

    @Test
    void findMany() {
    }

}