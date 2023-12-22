package BO;

import BO.Custom.OrderBo;
import BO.Custom.impl.CustomerBOImpl;
import BO.Custom.impl.ItemBoImpl;
import BO.Custom.impl.OrderBoImpl;
import BO.Custom.impl.OrderDetailsBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory(){

    }

    public static BoFactory getInstance() {
        return boFactory!=null? boFactory : (boFactory = new BoFactory());
    }

    public enum BoType{
        CUSTOMER,ITEM,ORDERS,ORDER_DETAILS
    }

    public <T extends SuperBo>T getBoType(BoType type){
        switch (type){
            case CUSTOMER: return (T) new CustomerBOImpl();
            case ITEM: return (T) new ItemBoImpl();
            case ORDERS: return (T) new OrderBoImpl();
            case ORDER_DETAILS: return (T) new OrderDetailsBoImpl();
            default: return null;
        }
    }
}

