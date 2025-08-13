package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCustomerFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnView;

    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/update_choice_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        currentStage.close();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewOnAction(ActionEvent event) {

    }

}
