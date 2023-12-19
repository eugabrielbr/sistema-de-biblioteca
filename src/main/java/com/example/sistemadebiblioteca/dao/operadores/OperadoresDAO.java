package main.dao.operadores;

import main.dao.CRUD;
import main.model.Operadores;

import java.util.List;

/**
 * Interface OperadoresDAO
 * @author Gabriel
 */
public interface OperadoresDAO extends CRUD<Operadores> {

    /**
     * busca objeto(s)
     * @return List<Operadores>
     */
    public List<Operadores> getAdministradores();

    /**
     * busca objeto(s)
     * @return List<Operadores>
     */
    public List<Operadores> getBibliotecarios();

}
