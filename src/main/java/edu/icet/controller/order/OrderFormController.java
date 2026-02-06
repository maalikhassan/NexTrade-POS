package edu.icet.controller.order;

import edu.icet.model.dto.ItemDto;
import edu.icet.service.customer.CustomerFormService;
import edu.icet.service.customer.CustomerServiceImpl;
import edu.icet.service.item.ItemFormService;
import edu.icet.service.item.ItemServiceImpl;
import edu.icet.service.order.OrderFormService;
import edu.icet.service.order.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OrderFormController {

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
    private TableView<?> tblCartItems;

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

    @FXML
    void btnAddToCartAction(ActionEvent event) {

        String cusId = txtCusId.getText();
        String cusName = txtCusName.getText();
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String unitPrice = txtUnitPrice.getText();
        String qty = txtQty.getText();
        String discount = txtDiscount.getText();

        // Here we can add the logic to add the item to the cart and update the table view accordingly
        // We can also calculate the total price for the item and update the net total label



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
