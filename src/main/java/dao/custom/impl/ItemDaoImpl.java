package dao.custom.impl;

import dao.util.CrudUtil;
import dao.custom.ItemDao;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Item VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Item VALUES(?,?,?,?)";
        return CrudUtil.execute(sql,entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {

        return null;
    }

    @Override
    public boolean saveItem(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(Item dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
