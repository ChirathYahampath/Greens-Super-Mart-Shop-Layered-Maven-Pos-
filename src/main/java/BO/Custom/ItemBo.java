package BO.Custom;

import BO.SuperBo;
import dto.itemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {
    boolean saveItem(itemDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(itemDTO dto) throws SQLException, ClassNotFoundException;
    List<itemDTO> findAllItems() throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;
}
