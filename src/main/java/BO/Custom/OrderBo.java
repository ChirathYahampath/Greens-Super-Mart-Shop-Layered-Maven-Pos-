package BO.Custom;

import BO.SuperBo;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.itemDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    boolean saveOrder(OrderDTO dto)throws SQLException, ClassNotFoundException;

    itemDTO findItem(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException;

    List<itemDTO> findAllItems() throws SQLException, ClassNotFoundException;

    List<CustomerDTO> findAllCustomers() throws SQLException, ClassNotFoundException;

    String generateId() throws SQLException, ClassNotFoundException;
}
