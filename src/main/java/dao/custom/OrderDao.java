package dao.custom;

import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    boolean save(OrderDto dto) throws SQLException, ClassNotFoundException;

    boolean update(OrderDto entity) throws SQLException, ClassNotFoundException;

    boolean delete(String value) throws SQLException, ClassNotFoundException;

    List<OrderDto> getAll() throws SQLException, ClassNotFoundException;

    OrderDto getLastOrder() throws SQLException, ClassNotFoundException;
}
