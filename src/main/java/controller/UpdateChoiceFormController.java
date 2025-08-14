package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateChoiceFormController {

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnUpdateCustomer;

    @FXML
    private JFXButton btnUpdateItem;

    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        nextStage(event, "/view/login_form.fxml");
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
        nextStage(event, "/view/update_customer_form.fxml");
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        nextStage(event, "/view/update_item_form.fxml");
    }

    private void nextStage(ActionEvent event, String path) {
        currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        currentStage.close();
    }

}
