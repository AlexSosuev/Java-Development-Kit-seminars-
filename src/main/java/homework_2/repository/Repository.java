package homework_2.repository;

public interface Repository<T> {
    void save(T message);
    T load();
}