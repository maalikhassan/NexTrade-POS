package edu.icet.controller.customer;

import edu.icet.model.dto.CustomerDto;
import edu.icet.repository.CustomerRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CustomerFormController implements Initializable {

    CustomerRepo customerRepo = new CustomerRepo();
    private final Map<String, List<String>> cityMap = new HashMap<>();
    ObservableList<CustomerDto> customerDtoObservableList = FXCollections.observableArrayList();
    /*-----------------------------------------*/
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostal;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ComboBox<String> comboCity;

    @FXML
    private ComboBox<String> comboProvince;

    @FXML
    private ComboBox<String> comboTitle;

    @FXML
    private DatePicker dateCusDob;

    @FXML
    private TableView<CustomerDto> tblCustomers;

    @FXML
    private TextArea txtCusAddress;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusPostal;

    @FXML
    private TextField txtCusSalary;

    @FXML
    private TextField txtSearch;
    /*-----------------------------------------*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostal.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomers.setItems(customerDtoObservableList);

        loadCityMap(); // helper method below
        loadCustomerTable();

        // Set Provinces from the Map keys
        comboProvince.setItems(FXCollections.observableArrayList(cityMap.keySet()));
        comboTitle.setItems(FXCollections.observableArrayList("Mr", "Ms", "Mrs"));

        // CASCADING LOGIC: Listen for Province changes
        comboProvince.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Populate City ComboBox based on the selected Province
                List<String> cities = cityMap.get(newValue);
                comboCity.setItems(FXCollections.observableArrayList(cities));
            } else {
                comboCity.getItems().clear();
            }
        });

        //set selected row data to the fields
        tblCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {;
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    private void loadCustomerTable() {
        customerDtoObservableList.clear();
        tblCustomers.setItems(customerRepo.getAllCustomers());
    }

    /*-----------------------------------------*/


    @FXML
    void btnAddAction(ActionEvent event) {
        String id = txtCusId.getText();
        String title = comboTitle.getValue();
        String name = txtCusName.getText();
        LocalDate dob = dateCusDob.getValue();
        Double salary = Double.parseDouble(txtCusSalary.getText());
        String address = txtCusAddress.getText();
        String city = comboCity.getValue();
        String province = comboProvince.getValue();
        String postalCode = txtCusPostal.getText();

        customerRepo.addCustomer(id, title, name, java.sql.Date.valueOf(dob), salary, address, city, province, postalCode);
        loadCustomerTable();
        clearFields();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        String id = txtCusId.getText();
        String title = comboTitle.getValue();
        String name = txtCusName.getText();
        LocalDate dob = dateCusDob.getValue();
        Double salary = Double.parseDouble(txtCusSalary.getText());
        String address = txtCusAddress.getText();
        String city = comboCity.getValue();
        String province = comboProvince.getValue();
        String postalCode = txtCusPostal.getText();

        customerRepo.updateCustomer(id, title, name, java.sql.Date.valueOf(dob), salary, address, city, province, postalCode);
        loadCustomerTable();
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        String id = txtCusId.getText();
        customerRepo.deleteCustomer(id);
        clearFields();
        loadCustomerTable();
    }

    @FXML
    void btnLoadAction(ActionEvent event) {
        loadCustomerTable();
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        clearFields();
    }

    private void loadCityMap() {
        cityMap.put("Western", Arrays.asList("Colombo", "Negombo", "Panadura", "Kalutara", "Gampaha"));
        cityMap.put("Central", Arrays.asList("Kandy", "Matale", "Nuwara Eliya", "Dambulla"));
        cityMap.put("Southern", Arrays.asList("Galle", "Matara", "Hambantota", "Tangalle"));
        cityMap.put("Northern", Arrays.asList("Jaffna", "Vavuniya", "Mannar", "Mullaitivu"));
        cityMap.put("Eastern", Arrays.asList("Trincomalee", "Batticaloa", "Ampara"));
        cityMap.put("North Western", Arrays.asList("Kurunegala", "Puttalam", "Chilaw"));
        cityMap.put("North Central", Arrays.asList("Anuradhapura", "Polonnaruwa"));
        cityMap.put("Uva", Arrays.asList("Badulla", "Bandarawela", "Moneragala"));
        cityMap.put("Sabaragamuwa", Arrays.asList("Ratnapura", "Kegalle", "Embilipitiya"));
    }

    private void setSelectedValue(CustomerDto selectedValue) {
        if (selectedValue == null) {
            clearFields();
            return;
        }
        txtCusId.setText(selectedValue.getId());
        comboTitle.setValue(selectedValue.getTitle());
        txtCusName.setText(selectedValue.getName());
        dateCusDob.setValue(LocalDate.parse(selectedValue.getDob().toString()));
        txtCusSalary.setText(String.valueOf(selectedValue.getSalary()));
        txtCusAddress.setText(selectedValue.getAddress());
        comboCity.setValue(selectedValue.getCity());
        comboProvince.setValue(selectedValue.getProvince());
        txtCusPostal.setText(selectedValue.getPostalCode());
    }

    public void clearFields(){
        txtCusId.clear();
        comboTitle.setValue(null);
        txtCusName.clear();
        dateCusDob.setValue(null);
        txtCusSalary.clear();
        txtCusPostal.clear();
        txtCusAddress.clear();
        comboCity.setValue(null);
        comboProvince.setValue(null);
    }

}
