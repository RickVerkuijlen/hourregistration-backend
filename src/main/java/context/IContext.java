package context;

import java.util.UUID;

public interface IContext<T> {

    /**
     * Deletes existing entity in the database of type T
     * @param entity The object of type T that needs to be deleted
     * @return if query was successful or not
     */
    boolean delete(UUID entity);

    /**
     * Updates the existing entity in the database of type T
     * @param entity The object of type T that needs to be updated
     * @return if query was successful or not
     */
    boolean update(T entity);

    /**
     * Creates a new entity in the database of type T
     * @param entity The object of type T that needs to be put in the database
     * @return if query was successful or not
     */
    boolean create(T entity);

    /**
     * Gets the existing entity from the database
     * @param entity the entity that needs to be found
     * @return the object of type T
     */
    T read(UUID entity);
}
