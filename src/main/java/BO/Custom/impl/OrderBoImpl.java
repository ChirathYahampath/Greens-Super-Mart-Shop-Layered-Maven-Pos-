package BO.Custom.impl;

import BO.Custom.OrderBo;
import DAO.DaoFactory;
import DAO.Custom.CustomerDAO;
import DAO.Custom.ItemDAO;
import DAO.Custom.OrderDAO;
import DAO.Custom.OrderDetailsDAO;
import db.DBConnection;
import dto.CustomerDTO;
import dto.itemDTO;
import dto.OrderDetailsDTO;
import dto.OrderDTO;
import entity.Customer;
import entity.Item;
import entity.Orders;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    OrderDAO orderDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDERS);
    OrderDetailsDAO orderDetailsDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDER_DETAILS);
    ItemDAO itemDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ITEM);
    CustomerDAO customerDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.CUSTOMER);

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        DBConnection.getInstance().getConnection().setAutoCommit(false);

        Orders order = new Orders(
                dto.getOrderId(),
                dto.getDate(),
                dto.getCustId()
        );

        boolean orderPlaced = orderDao.save(order);

        boolean isOrderPlaced = true;
        if (orderPlaced) {
            for (OrderDetailsDTO detail:dto.getDetails()) {

                boolean detailSaved = orderDetailsDao.save(new OrderDetails(
                        detail.getOrderId(),
                        detail.getItemCode(),
                        detail.getQty(),
                        detail.getUnitPrice()
                ));

                if (!detailSaved){
                    isOrderPlaced = false;
                }

            }
        }else{
            isOrderPlaced = false;
        }

        if (isOrderPlaced){

            DBConnection.getInstance().getConnection().commit();

            return true;
        }else{
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }
    }

    @Override
    public itemDTO findItem(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDao.find(id);
        return new itemDTO(
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        );
    }

    @Override
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.find(id);
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()
        );
    }

    @Override
    public List<itemDTO> findAllItems() throws SQLException, ClassNotFoundException {
        List<itemDTO> list = new ArrayList<>();

        for (Item item:itemDao.findAll()) {
            list.add(new itemDTO(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
            ));
        }

        return list;
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
    public String generateId() throws SQLException, ClassNotFoundException {
        String id = orderDao.findLastId();

        if (id!=null){
            int num = Integer.parseInt(id.split("[D]")[1]);
            num++;
            return String.format("D%03d",num);
        }else {
            return "D001";
        }
    }
}
