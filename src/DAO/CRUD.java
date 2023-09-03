package DAO;

import java.util.Map;
public interface CRUD<T> {

    public void create(T obj);

    /**
     * Deleta um objeto
     *
     * @param obj
     * @return
     */
    public void delete(T obj);

    /**
     * Detela todos os dados
     */
    public void deleteMany();

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
