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
        try {
            // 1. Check for empty fields
            if (isAnyFieldEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill in all required fields!").show();
                return;
            }

            String itemCode = txtItemCode.getText();
            int requestedQty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double discountPercent = txtDiscount.getText().isEmpty() ? 0 : Double.parseDouble(txtDiscount.getText());

            // 2. Business Rule Validation
            if (requestedQty <= 0) {
                new Alert(Alert.AlertType.ERROR, "Quantity must be greater than zero!").show();
                return;
            }

            if (discountPercent < 0 || discountPercent > 100) {
                new Alert(Alert.AlertType.ERROR, "Discount must be between 0% and 100%!").show();
                return;
            }

            // 3. Stock Check (Crucial for Order Management)
            ItemDto item = orderFormService.searchItem(itemCode);
            if (requestedQty > item.getQtyOnHand()) {
                new Alert(Alert.AlertType.WARNING, "Insufficient Stock! Available: " + item.getQtyOnHand()).show();
                return;
            }

            // 4. Calculate Line Total (Percentage-based)
            double total = (unitPrice * requestedQty) * (1 - (discountPercent / 100));

            // 5. Deduplication Logic: Update if already in cart
            for (OrderDetailDto cartItem : cartItems) {
                if (cartItem.getItemCode().equals(itemCode)) {
                    // Check if NEW total quantity exceeds stock
                    if ((cartItem.getQty() + requestedQty) > item.getQtyOnHand()) {
                        new Alert(Alert.AlertType.ERROR, "Combined quantity exceeds available stock!").show();
                        return;
                    }
                    cartItem.setQty(cartItem.getQty() + requestedQty);
                    cartItem.setTotal(cartItem.getTotal() + total);
                    tblCartItems.refresh();
                    calculateNetTotal();
                    return;
                }
            }

            // 6. Add New Item if not a duplicate
            cartItems.add(new OrderDetailDto(itemCode, txtDescription.getText(), requestedQty, unitPrice, discountPercent, total));
            calculateNetTotal();

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format in Quantity or Discount!").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage()).show();
        }
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;

        // Loop through every item currently in your TableView's list
        for (OrderDetailDto item : cartItems) {
            // Add each row's total (already calculated in btnAddToCartAction)
            netTotal += item.getTotal();
        }

        // Display the result in your label, formatted to 2 decimal places
        lblNetTotal.setText(String.format("%.2f", netTotal));
    }

    // Helper method to keep the main action clean
    private boolean isAnyFieldEmpty() {
        return txtCusId.getText().isEmpty() || txtItemCode.getText().isEmpty() || txtQty.getText().isEmpty();
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        //three things should happen here
        //1. Save order to database (Order and OrderDetails)
        //2. Update item stock in database
        //3. Clear the form for new order
        // we should also consider using transactions to ensure data integrity during these operations
        // if any of the operations fail, we can roll back the transaction to maintain a consistent state in the database
        //and we should also handle any exceptions that might occur during these operations and
        // show appropriate messages to the user

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
