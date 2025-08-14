package controller;

import com.jfoenix.controls.JFXButton;
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

    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "INSERT INTO items VALUES(?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtItemCode.getText());
            preparedStatement.setObject(2, txtDescription.getText());
            preparedStatement.setObject(3, txtPackSize.getText());
            preparedStatement.setObject(4, Double.parseDouble(txtUnitPrice.getText()));
            preparedStatement.setObject(5, Integer.parseInt(txtQtyOnHand.getText()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "DELETE FROM items WHERE item_code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtItemCode.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "UPDATE items SET description = ?, pack_size = ?, unit_price = ?, quantity_on_hand = ? WHERE item_code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtDescription.getText());
            preparedStatement.setObject(2, txtPackSize.getText());
            preparedStatement.setObject(3, Double.parseDouble(txtUnitPrice.getText()));
            preparedStatement.setObject(4, Integer.parseInt(txtQtyOnHand.getText()));
            preparedStatement.setObject(5, txtItemCode.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "SELECT * FROM items WHERE item_code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtItemCode.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
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
