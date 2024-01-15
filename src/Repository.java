import java.io.IOException;
import java.util.List;

public interface Repository<T> {

    void save(T t) throws IOException;
    void update(T t) throws IOException;
    List<T> findAll();
    void delete(T t) throws IOException;
}
