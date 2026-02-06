package edu.icet.controller.order;

import edu.icet.model.dto.ItemDto;
import edu.icet.model.dto.OrderDetailDto;
import edu.icet.service.order.OrderFormService;
import edu.icet.service.order.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    private final ObservableList<OrderDetailDto> cartItems = FXCollections.observableArrayList();

//    ItemFormService itemFormService = new ItemServiceImpl();
//    CustomerFormService customerFormService = new CustomerServiceImpl(); we properly seperate the layers
    OrderFormService orderFormService = new OrderServiceImpl(); //we put both in this and properly-
    // associated this layer to relevant service

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<OrderDetailDto> tblCartItems;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up Table Columns (ensure these IDs match your FXML)
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblCartItems.setItems(cartItems);
    }

    @FXML
    void btnAddToCartAction(ActionEvent event) {

        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double discount = txtDiscount.getText().isEmpty() ? 0 : Double.parseDouble(txtDiscount.getText());

        //1. Calculate Total for this line item
        double total = (unitPrice * qty) - discount;

        // 2. Check if item already exists in the cart
        for (OrderDetailDto item : cartItems) {
            if (item.getItemCode().equals(itemCode)) {
                item.setQty(item.getQty() + qty);
                item.setTotal(item.getTotal() + total);
                tblCartItems.refresh();
                calculateNetTotal();
                return;
            }
        }

        // 3. Add new item to cart if it's not a duplicate
        cartItems.add(new OrderDetailDto(itemCode, description, qty, unitPrice, discount, total));
        calculateNetTotal();
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (OrderDetailDto item : cartItems) {
            netTotal += item.getTotal();
        }
        lblNetTotal.setText(String.format("%.2f", netTotal));
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {

    }

    @FXML
    void txtCusIdAction(ActionEvent event) {
        String cusId = txtCusId.getText();
        String cusName = orderFormService.getCustomerNameById(cusId);

        if (cusName != null) {
            txtCusName.setText(cusName);
            txtItemCode.requestFocus();
        } else {
            new Alert(Alert.AlertType.WARNING, "Customer Not Found!").show();
            txtCusName.clear();
        }

    }

    @FXML
    void txtDiscountAction(ActionEvent event) {

    }

    @FXML
    void txtItemCodeAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        ItemDto itemDto = orderFormService.searchItem(itemCode);

        if (itemDto != null) {
            txtDescription.setText(itemDto.getDescription());
            txtUnitPrice.setText(String.valueOf(itemDto.getUnitPrice()));
            txtQty.requestFocus();
        } else {
            new Alert(Alert.AlertType.WARNING, "Item Not Found!").show();
            txtDescription.clear();
            txtUnitPrice.clear();
            txtQty.clear();
        }

    }

    @FXML
    void txtQtyAction(ActionEvent event) {

    }

}
