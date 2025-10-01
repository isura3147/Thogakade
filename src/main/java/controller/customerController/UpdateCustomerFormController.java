package controller.customerController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerFormController implements Initializable {

    private final Stage stage = new Stage();
    @FXML
    public TextField txtPostalCode;
    @FXML
    public TableView<Customer> tblCustomers;
    ObservableList<Customer> customerInfos = FXCollections.observableArrayList();
    CustomerService customerService = new CustomerController();
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
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colCity;
    @FXML
    private TableColumn<?, ?> colDOB;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colPostalCode;
    @FXML
    private TableColumn<?, ?> colProvince;
    @FXML
    private TableColumn<?, ?> colSalary;
    @FXML
    private TableColumn<?, ?> colTitle;
    private Stage currentStage;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerService.addCustomer(txtId.getText(), txtTitle.getText(), txtName.getText(), String.valueOf(txtDOB.getValue()),
                Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(),
                txtProvince.getText(), txtPostalCode.getText());

        loadCustomerInfo();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerService.deleteCustomer(txtId.getText());
        loadCustomerInfo();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        customerService.updateCustomer(txtId.getText(), txtTitle.getText(), txtName.getText(), String.valueOf(txtDOB.getValue()),
                Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(),
                txtProvince.getText(), txtPostalCode.getText());

        loadCustomerInfo();
    }

    @FXML
    void btnViewOnAction(ActionEvent event) throws SQLException {

        ResultSet resultSet = customerService.viewCustomer(txtId.getText());

        while (resultSet.next()) {
            txtTitle.setText(resultSet.getString("CustTitle"));
            txtName.setText(resultSet.getString("CustName"));
            txtDOB.setValue(resultSet.getDate("DOB").toLocalDate());
            txtSalary.setText(String.valueOf(resultSet.getDouble("salary")));
            txtAddress.setText(resultSet.getString("CustAddress"));
            txtCity.setText(resultSet.getString("City"));
            txtProvince.setText(resultSet.getString("Province"));
            txtPostalCode.setText(resultSet.getString("PostalCode"));
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        nextStage(event, "/view/update_choice_form.fxml");
    }


    private void nextStage(ActionEvent event, String path) {
        currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        currentStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadCustomerInfo();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                txtId.setText(newValue.getId());
                txtTitle.setText(newValue.getTitle());
                txtName.setText(newValue.getName());
                txtDOB.setValue(newValue.getDob());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        }));

    }

    private void loadCustomerInfo() {
        customerInfos.clear();
        tblCustomers.setItems(customerService.loadDetails(customerInfos));
    }
}
