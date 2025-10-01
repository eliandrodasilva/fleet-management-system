package dao;

public interface GenericDAO<T, ID>  {
        void save(T entity);
        T findById(ID id);
        T update(T entity);
        void delete(T entity);
    }
