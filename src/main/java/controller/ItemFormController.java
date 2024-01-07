package controller;

import bo.custom.ItemBo;
import bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.ItemDto;
import dto.tm.ItemTm;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ItemFormController {
    public BorderPane pane;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtSearch;
    public JFXTreeTableView<ItemTm> tblItem;
    public TreeTableColumn colCode;
    public TreeTableColumn colDesc;
    public TreeTableColumn colUnitPrice;
    public TreeTableColumn colQty;
    public TreeTableColumn colOption;

    private ItemBo itemBo = new ItemBoImpl();

    public void initialize() {
        colCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new TreeItemPropertyValueFactory<>("desc"));
        colUnitPrice.setCellValueFactory(new TreeItemPropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new TreeItemPropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));
        loadItems();


    }

    
          //  txtSearch.textProperty().addListener(new ChangeListener<String>() {
    //            @Override
    //            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
    //                tblItem.setPredicate(new Predicate<TreeItem<ItemTm>>() {
    //                    @Override
    //                    public boolean test(TreeItem<ItemTm> treeItem) {
    //                        return treeItem.getValue().getCode().contains(newValue) ||
    //                                treeItem.getValue().getCode().toLowerCase().contains(newValue) ||
    //                                treeItem.getValue().getDesc().contains(newValue) ||
    //                                treeItem.getValue().getDesc().toLowerCase().contains(newValue);
    //                    }
    //                });
    //            }
    //        });


        private void loadItems () {
            ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

            try {
                List<ItemDto> dtoList = itemBo.allItems();
                for (ItemDto dto : dtoList) {
                    JFXButton btn = new JFXButton("Delete");

                    ItemTm tm = new ItemTm(
                            dto.getCode(),
                            dto.getDesc(),
                            dto.getUnitPrice(),
                            dto.getQty(),
                            btn
                    );

                    btn.setOnAction(actionEvent -> {
                    deleteItem(tm.getCode());
                    });

                    tmList.add(tm);
                }
                RecursiveTreeItem<ItemTm> treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
                tblItem.setRoot(treeItem);
                tblItem.setShowRoot(false);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public void backButtonOnAction (ActionEvent actionEvent){
            Stage stage = (Stage) txtCode.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void saveButtonOnAction (ActionEvent actionEvent){
            try {
                boolean isSaved = itemBo.addItem(new ItemDto(txtCode.getText(), txtDescription.getText(),
                        Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQty.getText()))
                );
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION,"Item Saved!").show();
                    loadItems();
                    clearFields();
                    tblItem.refresh();
                }
            } catch (SQLIntegrityConstraintViolationException ex){
                new Alert(Alert.AlertType.ERROR,"Duplicate Entry").show();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        public void updateButtonOnAction (ActionEvent actionEvent){
            try {
                boolean isUpdated = itemBo.updateItem(new ItemDto(txtCode.getText(), txtDescription.getText(),
                        Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQty.getText()))
                );
                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION,"Item Updated!").show();
                    loadItems();
                    tblItem.refresh();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    private void deleteItem (String id) {

        try {
            boolean isDeleted = itemBo.deleteItem(id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"item Deleted!").show();
                loadItems();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        tblItem.refresh();
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        txtCode.setEditable(true);
    }

}
