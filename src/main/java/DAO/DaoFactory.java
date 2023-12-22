package DAO;

import DAO.Custom.impl.CustomerDaoImpl;
import DAO.Custom.impl.ItemDaoImpl;
import DAO.Custom.impl.OrderDaoImpl;
import DAO.Custom.impl.OrderDetailsDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public enum DaoType{
        CUSTOMER,ITEM,ORDERS,ORDER_DETAILS
    }

    public <T extends SuperDAO>T getDaoType(DaoType type){
        switch (type){
            case CUSTOMER: return (T) new CustomerDaoImpl();
            case ITEM: return (T) new ItemDaoImpl();
            case ORDERS: return (T) new OrderDaoImpl();
            case ORDER_DETAILS: return (T) new OrderDetailsDaoImpl();
            default:return null;
        }
    }
}
