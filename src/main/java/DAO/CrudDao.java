package DAO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao <T,ID> extends SuperDAO{
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    boolean update(T entity) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    List<T> findAll() throws SQLException, ClassNotFoundException;
    ID findLastId() throws SQLException, ClassNotFoundException;
    T find(ID id) throws SQLException, ClassNotFoundException;

}
