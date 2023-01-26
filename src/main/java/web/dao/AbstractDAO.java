package web.dao;

import java.util.List;

public interface AbstractDAO<T> {
    void create(T o);
    T read(T o);
    void update(T o);
    void delete(T o);
    List<T> getAll();
}
