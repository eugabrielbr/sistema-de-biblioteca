package test.DAO;

import main.dao.livro.LivroDAOmap;
import main.exceptions.crud.CrudExceptions;
import main.model.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

class LivroDAOmapTest {

    LivroDAOmap obj = new LivroDAOmap();
    public void addLivro(){

        Livro objLivro = new Livro("teoria da relatividade","albert einstein",null,2222,"fisica",null);
        obj.create(objLivro);


    }
    @Test
    void findByAutor() throws CrudExceptions {

        addLivro();

        List<Livro> var =  obj.findByAutor("albert einstein");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);

    }

    @Test
    void findByTitulo() throws CrudExceptions {

        addLivro();

        List<Livro> var =  obj.findByTitulo("teoria da relatividade");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);

    }

    @Test
    void findByCategoria() throws CrudExceptions {

        addLivro();

        List<Livro> var =  obj.findByCategoria("fisica");
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);
    }

    @Test
    void findByISBN() throws CrudExceptions {

        addLivro();

        List<Livro> var = obj.findByISBN(2222);
        Assertions.assertNotNull(var); //verifica se algo é retornado
        Assertions.assertFalse(var.isEmpty()); //verifica se "pescou" o livro esperado

        //System.out.println(var);
    }

    @Test
    void findById() throws CrudExceptions {

        addLivro();
        Assertions.assertNotNull(obj.findById(1));
    }

    @Test
    void create() throws CrudExceptions {

        addLivro();
        Assertions.assertNotNull(obj.findById(1));

    }

    @Test
    void delete(){

        addLivro();

        try{
            obj.delete(1);
        }
        catch (CrudExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void deleteMany() throws CrudExceptions {

        addLivro();
        obj.deleteMany();
        Assertions.assertTrue(obj.acervo.isEmpty());

    }

    @Test
    void update() throws CrudExceptions {

        addLivro();

        Livro objLivro = new Livro("teoria da relativida","albert einstein",null,2222,"fisica",null);

        try{
            obj.update(objLivro,1);
        }
        catch (CrudExceptions e){
            Assertions.fail();
        }

    }

    @Test
    void findMany() {


        Map<Integer,Livro> map = obj.findMany();
        Assertions.assertNotNull(map);




    }

}