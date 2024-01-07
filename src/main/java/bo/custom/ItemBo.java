package bo.custom;

import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo {
    boolean addItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteItem (String code) throws SQLException, ClassNotFoundException;

    List<ItemDto> allItems() throws SQLException, ClassNotFoundException;
}
