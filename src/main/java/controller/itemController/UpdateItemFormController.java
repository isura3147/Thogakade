package controller.itemController;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class UpdateItemFormController {

    @FXML
    public JFXButton btnViewAllItems;

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

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    public TextField txtUnitPrice;

    ItemService itemService = new ItemController();
    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        itemService.addItem(txtItemCode.getText(), txtDescription.getText(), txtPackSize.getText(),
                            Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        itemService.deleteItem(txtItemCode.getText());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        itemService.updateItem(txtDescription.getText(), txtPackSize.getText(), Double.parseDouble(txtUnitPrice.getText()),
                                Integer.parseInt(txtQtyOnHand.getText()), txtItemCode.getText());
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws SQLException {
        ResultSet resultSet = itemService.viewItem(txtItemCode.getText());
        try {
            while(resultSet.next()) {
                txtDescription.setText(resultSet.getString("description"));
                txtPackSize.setText(resultSet.getString("pack_size"));
                txtUnitPrice.setText(String.valueOf(resultSet.getDouble("unit_price")));
                txtQtyOnHand.setText(String.valueOf(resultSet.getInt("quantity_on_hand")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        nextStage(event, "/view/update_choice_form.fxml");
    }

    @FXML
    public void btnViewAllItemsOnAction(ActionEvent event) {
        nextStage(event, "/view/all_items_table.fxml");
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
