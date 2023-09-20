package main.dao.emprestimo;

import main.dao.CRUD;
import main.model.Emprestimo;

import java.util.List;

public interface EmprestimoDAO extends CRUD<Emprestimo> {
    List<Emprestimo> findByUser(Integer IDuser);
}
