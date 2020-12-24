package cl.escalab.crochicat.service;
import java.util.List;
import java.util.UUID;

public interface Iservice<T> {
    List<T> getAll();
    T save(T obj);
    T get(UUID id);
    Boolean delete(UUID id);
    T update(T t, UUID id);
}
