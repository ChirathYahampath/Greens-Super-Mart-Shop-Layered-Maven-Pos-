package BO.Custom;

import dto.CustomerDTO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import dto.itemDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDeatilsBo {
    List<OrderDetailsDTO> findOrderDetailByOrderId(String id) throws SQLException, ClassNotFoundException;

    itemDTO findItem(String itemCode) throws SQLException, ClassNotFoundException;

    List<OrderDTO> findAllOrders() throws SQLException, ClassNotFoundException;

    boolean deleteOrder(String orderId) throws SQLException, ClassNotFoundException;

    CustomerDTO findCustomer(String customerId) throws SQLException, ClassNotFoundException;
}
