package BO.Custom;

import BO.SuperBo;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    List<CustomerDTO> findAllCustomers() throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    String generateId() throws SQLException, ClassNotFoundException;

    List<CustomerDTO> allCustomers() throws SQLException, ClassNotFoundException;
}
