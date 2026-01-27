package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {
    @FXML
    private AnchorPane contentPane; // The ID from your FXML

    public void loadCustomerView(ActionEvent event) throws IOException {
        setNode("CustomerForm.fxml");
    }

    public void loadItemView(ActionEvent event) throws IOException {
        setNode("ItemForm.fxml");
    }

    public void loadDashboardView(ActionEvent actionEvent) throws IOException {
        setNode("Home.fxml");
    }

    public void loadOrderView(ActionEvent actionEvent) throws IOException {
        setNode("OrderForm.fxml");
    }

    // A helper method to keep your code clean and dry
    private void setNode(String fxmlPath) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/" + fxmlPath));
        contentPane.getChildren().setAll(node); // This swaps the content instantly

        // Ensure the sub-view fills the whole pane
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }


}
