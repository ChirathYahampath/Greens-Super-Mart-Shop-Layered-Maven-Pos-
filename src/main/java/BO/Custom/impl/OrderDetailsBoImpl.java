package BO.Custom.impl;

import BO.Custom.OrderDeatilsBo;
import DAO.DaoFactory;
import DAO.Custom.CustomerDAO;
import DAO.Custom.ItemDAO;
import DAO.Custom.OrderDAO;
import DAO.Custom.OrderDetailsDAO;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.itemDTO;
import dto.OrderDetailsDTO;
import entity.Customer;
import entity.Item;
import entity.Orders;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBoImpl implements OrderDeatilsBo {
    OrderDetailsDAO orderDetailsDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDER_DETAILS);
    ItemDAO itemDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ITEM);
    OrderDAO orderDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.ORDERS);
    CustomerDAO customerDao = DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.CUSTOMER);

    @Override
    public List<OrderDetailsDTO> findOrderDetailByOrderId(String id) throws SQLException, ClassNotFoundException {
        List<OrderDetailsDTO> list = new ArrayList<>();
        for (OrderDetails detail:orderDetailsDao.findOrderDetailByOrderId(id)) {
            list.add(new OrderDetailsDTO(
                    detail.getOrderId(),
                    detail.getItemCode(),
                    detail.getQty(),
                    detail.getUnitPrice()
            ));
        }
        return list;
    }

    @Override
    public itemDTO findItem(String itemCode) throws SQLException, ClassNotFoundException {
        Item item = itemDao.find(itemCode);
        return new itemDTO(
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        );
    }

    @Override
    public List<OrderDTO> findAllOrders() throws SQLException, ClassNotFoundException {
        List<OrderDTO> list = new ArrayList<>();
        List<OrderDetailsDTO> details = new ArrayList<>();

        for (Orders order:orderDao.findAll()) {
            for (OrderDetails orderDetail:orderDetailsDao.findOrderDetailByOrderId(order.getId())) {
                details.add(new OrderDetailsDTO(
                        orderDetail.getOrderId(),
                        orderDetail.getItemCode(),
                        orderDetail.getQty(),
                        orderDetail.getUnitPrice()
                ));
            }

            list.add(new OrderDTO(
                    order.getId(),
                    order.getDate(),
                    order.getCustomerId(),
                    details
            ));
        }
        return list;
    }

    @Override
    public boolean deleteOrder(String orderId) throws SQLException, ClassNotFoundException {
        return orderDao.delete(orderId);
    }

    @Override
    public CustomerDTO findCustomer(String customerId) throws SQLException, ClassNotFoundException {
        Customer customer = customerDao.find(customerId);
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()
        );
    }
}
