package Controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    public JFXButton CustomerBtn;
    public AnchorPane Pane;

    @FXML
    public void CustomerButtonOnAction(ActionEvent actionEvent) {
        Stage stage= (Stage) Pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/CustomerForm.fxml"))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    @FXML
    void itemButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) Pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/itemForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void PlaceOrderbtnOnAction(ActionEvent actionEvent) {
    }
}
