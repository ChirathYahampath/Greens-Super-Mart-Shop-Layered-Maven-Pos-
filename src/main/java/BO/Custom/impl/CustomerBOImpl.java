package BO.Custom.impl;

import BO.Custom.CustomerBo;
import DAO.DaoFactory;
import DAO.Custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBo {
    CustomerDAO customerDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary()
        );
        return customerDao.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary()
        );
        return customerDao.update(customer);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() throws SQLException, ClassNotFoundException {
        List<CustomerDTO> list = new ArrayList<>();

        for (Customer customer:customerDao.findAll()) {
            list.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return list;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        String id = customerDao.findLastId();

        if (id!=null){
            int num = Integer.parseInt(id.split("[C]")[1]);
            num++;
            return String.format("C%03d",num);
        }
        return "C001";
    }

    @Override
    public List<CustomerDTO> allCustomers() throws SQLException, ClassNotFoundException {
        return null;
    }
}
