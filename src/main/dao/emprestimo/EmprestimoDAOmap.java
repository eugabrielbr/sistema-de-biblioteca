package main.dao.emprestimo;

import main.exceptions.crud.CrudExceptions;
import main.model.Emprestimo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
                            
public class EmprestimoDAOmap implements EmprestimoDAO{

    int ID;
    public Map<Integer, Emprestimo> emprestimoMap;


    public EmprestimoDAOmap(){

        this.ID = 0;
        this.emprestimoMap = new HashMap<>();

    }


    @Override
    public void create( Emprestimo emprestimo ) {

        ID++;
        emprestimo.setIDemprestimo(ID);
        emprestimoMap.put(ID,emprestimo);

    }

    @Override
    public void delete( int ID ) throws CrudExceptions {

        Emprestimo emprestimo = emprestimoMap.remove(ID);

        if (emprestimo == null){
            throw new CrudExceptions(CrudExceptions.DELETE,ID);
        }
    }

    @Override
    public void deleteMany() throws CrudExceptions {

        this.emprestimoMap = new HashMap<>();
        this.ID = 0;
        
    }

    @Override
    public void update( Emprestimo emprestimo, Integer number ) throws CrudExceptions {

        Emprestimo get = emprestimoMap.get(ID);

        if (get != null) {

            Integer newId = get.getIDemprestimo();
            emprestimoMap.put(newId, emprestimo);

        }
        else{
            throw new CrudExceptions(CrudExceptions.UPDATE,ID);
        }
        
    }

    @Override
    public Map<Integer, Emprestimo> findMany() {
        return emprestimoMap;
    }

    @Override
    public Emprestimo findById( int id ) throws CrudExceptions {

        Emprestimo busca = emprestimoMap.get(id);

        if (busca != null){
            return busca;
        }
        else{
            throw new CrudExceptions(CrudExceptions.NOT_FOUND,id);
        }
    }

    @Override
    public List<Emprestimo> findByUser(Integer IDuser) {

        List<Emprestimo> listaEmprestimo = new ArrayList<>();

        for (Entry<Integer,Emprestimo> x : emprestimoMap.entrySet()){

            if (x.getValue().getUsuario().getID() == IDuser){
                listaEmprestimo.add(x.getValue());
            }

        }

        return listaEmprestimo;
    }
}
