package ekke.spring.common;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CrudServices<T> {

    T add(final T dto);

    List<T> getAll();

    T update(final Long id, final T dto);

    void delete(final Long id);
}
