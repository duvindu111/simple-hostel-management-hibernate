package lk.ijse.simple_hostel_management_hibernate.repository;

import java.util.List;

public interface CrudRepository<T,ID> extends SuperRepository{

    void save(T entity);

    T get(ID id);

    void update(T entity);

    void delete(T entity);

    List<T> getDetailsToTableView();

}
