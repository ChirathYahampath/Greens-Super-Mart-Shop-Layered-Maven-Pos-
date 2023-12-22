package DAO.Custom;

import DAO.CrudDao;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDao<OrderDetails,String> {
    List<OrderDetails> findOrderDetailByOrderId(String id) throws SQLException, ClassNotFoundException;
}
