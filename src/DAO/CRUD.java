package DAO;

import java.util.Map;
import java.util.List;
public interface CRUD<T> {

    public void create(T obj);

    /**
     * Deleta um objeto
     *
     * @param obj
     * @return
     */
    public boolean delete(int ID);

    /**
     * Detela todos os dados
     */
    public void deleteMany(List<Integer> lista);

    /**
     * Atualiza um objeto
     *
     * @param obj
     * @return
     */
    public T update(T obj);

    /**
     * Ler toda a lista de dados
     *
     * @return
     */
    public Map<Integer,T> findMany();

    /**
     * Encontra um objeto específico pelo id
     *
     * @param id
     * @return
     */
    public T findById(int id);

}
