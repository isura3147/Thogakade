package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

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

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtTitle;

    @FXML
    public TextField txtPostalCode;

    private Stage stage = new Stage();
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtId.getText());
            preparedStatement.setObject(2, txtTitle.getText());
            preparedStatement.setObject(3, txtName.getText());
            preparedStatement.setObject(4, txtDOB.getValue());
            preparedStatement.setObject(5, Double.parseDouble(txtSalary.getText()));
            preparedStatement.setObject(6, txtAddress.getText());
            preparedStatement.setObject(7, txtCity.getText());
            preparedStatement.setObject(8, txtProvince.getText());
            preparedStatement.setObject(9, txtPostalCode.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtId.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "UPDATE customers SET title = ?, name = ?, date_of_birth = ?, salary = ?, address = ?, city = ?, province = ?, postal_code = ? WHERE customer_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtTitle.getText());
            preparedStatement.setObject(2, txtName.getText());
            preparedStatement.setObject(3, txtDOB.getValue());
            preparedStatement.setObject(4, Double.parseDouble(txtSalary.getText()));
            preparedStatement.setObject(5, txtAddress.getText());
            preparedStatement.setObject(6, txtCity.getText());
            preparedStatement.setObject(7, txtProvince.getText());
            preparedStatement.setObject(8, txtPostalCode.getText());
            preparedStatement.setObject(9, txtId.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "SELECT * FROM customers WHERE customer_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, txtId.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                txtTitle.setText(resultSet.getString("title"));
                txtName.setText(resultSet.getString("name"));
                txtDOB.setValue(resultSet.getDate("date_of_birth").toLocalDate());
                txtSalary.setText(String.valueOf(resultSet.getDouble("salary")));
                txtAddress.setText(resultSet.getString("address"));
                txtCity.setText(resultSet.getString("city"));
                txtProvince.setText(resultSet.getString("province"));
                txtPostalCode.setText(resultSet.getString("postal_code"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

}
