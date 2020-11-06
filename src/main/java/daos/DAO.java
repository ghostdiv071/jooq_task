package daos;

import java.util.List;

public interface DAO<T> {

    T get(int id);

    List<T> getAll();

    boolean save(T entity);

    boolean update(T entity);

    boolean delete(T entity);
}
