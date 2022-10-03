package ekke.spring.common;


import java.util.List;

public interface CrudServices<T> {

    T add(final T dto);

    List<T> getAll();

    T getById(final Long id);

    T update(final Long id, final T dto);

    void delete(final Long id);
}
