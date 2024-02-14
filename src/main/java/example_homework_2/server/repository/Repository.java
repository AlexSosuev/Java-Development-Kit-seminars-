package example_homework_2.server.repository;

public interface Repository<T> {
    void save(T text);
    T load();
}
