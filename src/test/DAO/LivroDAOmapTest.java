package test.DAO;

import main.dao.livro.LivroDAOmap;
import main.exceptions.livro.LivroExceptions;
import main.model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

class LivroDAOmapTest {

    LivroDAOmap obj = new LivroDAOmap();
    public void addLivro(){

        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",true,null);
        obj.create(objLivro);


    }
    @Test
    void findByAutor() throws LivroExceptions {

        addLivro();

        List<Livro> var =  obj.findByAutor("albert einstein");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);

    }

    @Test
    void findByTitulo() throws LivroExceptions {

        addLivro();

        List<Livro> var =  obj.findByTitulo("teoria da relatividade");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);

    }

    @Test
    void findByCategoria() throws LivroExceptions {

        addLivro();

        List<Livro> var =  obj.findByCategoria("fisica");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);
    }

    @Test
    void findByISBN() throws LivroExceptions {

        addLivro();

        List<Livro> var = obj.findByISBN(2222);
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);
    }

    @Test
    void findById() throws LivroExceptions  {

        addLivro();
        Assertions.assertNotNull(obj.findById(1));
    }

    @Test
    void create() throws LivroExceptions {

        addLivro();
        Assertions.assertNotNull(obj.findById(1));

    }

    @Test
    void delete(){

        addLivro();

        try{
            obj.delete(1);
        }
        catch (LivroExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void deleteMany() throws LivroExceptions {

        addLivro();
        obj.deleteMany();
        Assertions.assertTrue(obj.acervo.isEmpty());

    }

    @Test
    void update() throws LivroExceptions {

        addLivro();

        Livro objLivro = new Livro("teoria da relativ","albert einstein",null,2222,"fisica",true,null);

        try{
            obj.update(objLivro,1);
        }
        catch (LivroExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void findMany() {


        Map<Integer,Livro> map = obj.findMany();
        Assertions.assertNotNull(map);




    }

}