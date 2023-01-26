package web.services;

import java.util.List;

public interface AbstractService<T> {
    void create(T o);
    T read(T o);
    void update(T o);
    void delete(T o);
    List<T> getAll();
}
