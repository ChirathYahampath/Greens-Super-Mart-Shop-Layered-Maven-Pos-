package DAO.Custom;

import DAO.CrudDao;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDao<Customer,String> {

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
}
