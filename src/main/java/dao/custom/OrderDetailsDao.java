package dao.custom;

import dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDao {
    boolean saveOrderDetails(List<OrderDetailDto> list) throws SQLException, ClassNotFoundException;
}
