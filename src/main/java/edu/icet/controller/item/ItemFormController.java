package edu.icet.controller.item;

import edu.icet.model.dto.ItemDto;
import edu.icet.repository.ItemRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    //ItemRepo itemRepo = new ItemRepo(); //should pass to the interface, and int will pass to repo
    ItemFormService itemFormService = new ItemRepo();
    ObservableList<ItemDto> itemDtoObservableList = FXCollections.observableArrayList();

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
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colItemDesc;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<ItemDto> tblItems;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextArea txtItemDesc;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItems.setItems(itemDtoObservableList);

        loadItemtable();
        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setSelectedValue((ItemDto) newValue);
        });

    }

    private void loadItemtable() {
        itemDtoObservableList.clear();
        itemDtoObservableList.addAll(itemFormService.getAllItems());
    }

    @FXML
    void btnAddAction(ActionEvent event) {
        String code = txtItemCode.getText();
        String description = txtItemDesc.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        itemFormService.addItem(code, description, packSize, unitPrice, qtyOnHand);
        loadItemtable();
        clearFields();
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }
    @FXML
    void btnDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnLoadAction(ActionEvent event) {

    }

    @FXML
    void btnClearAction(ActionEvent event) {
        clearFields();
    }

    private void setSelectedValue(ItemDto selectedValue) {
        if (selectedValue == null) {
            clearFields();
            return;
        }
        txtItemCode.setText(selectedValue.getCode());
        txtItemDesc.setText(selectedValue.getDescription());
        txtPackSize.setText(selectedValue.getPackSize());
        txtQtyOnHand.setText(String.valueOf(selectedValue.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(selectedValue.getUnitPrice()));
    }

    public void clearFields(){
        txtItemCode.clear();
        txtItemDesc.clear();
        txtPackSize.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }

}
