package DAO.Custom.impl;

import DAO.Custom.OrderDAO;
import entity.Orders;
import util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDAO {
    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO orders VALUES(?,?,?)",
                entity.getId(),
                Date.valueOf(entity.getDate()),
                entity.getCustomerId()
        );
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "DELETE FROM orders WHERE id=?",
                id
        );
    }

    @Override
    public List<Orders> findAll() throws SQLException, ClassNotFoundException {
        List<Orders> list = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute(
                "SELECT * FROM orders"
        );

        while (resultSet.next()) {
            list.add(new Orders(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return list;
    }

    @Override
    public String findLastId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute(
                "SELECT id FROM orders ORDER BY id DESC LIMIT 1"
        );
        return resultSet.next()? resultSet.getString(1):null;
    }

    @Override
    public Orders find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
