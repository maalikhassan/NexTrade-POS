package edu.icet.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeController {
    @FXML
    private AnchorPane contentPane; // The ID from your FXML

    @FXML
    public void initialize() {
        try {
            // This sets the logo screen as the first thing the user sees
            setNode("Dash.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // This Map stores the views so we don't reload them from disk
    private final Map<String, Node> viewCache = new HashMap<>();

    public void loadCustomerView(ActionEvent event) throws IOException {
        setNode("CustomerForm.fxml");
    }

    public void loadItemView(ActionEvent event) throws IOException {
        setNode("ItemForm.fxml");
    }

    public void loadDashboardView(ActionEvent actionEvent) throws IOException {
        setNode("Dash.fxml");
    }

    public void loadOrderView(ActionEvent actionEvent) throws IOException {
        setNode("OrderForm.fxml");
    }

// A helper method to keep your code clean and dry
    private void setNode(String fxmlPath) throws IOException {

        Node node;

        if (viewCache.containsKey(fxmlPath)) {
            // If we already loaded it before, just grab it from RAM
        node = viewCache.get(fxmlPath);
        } else {
            // First time loading? Read from disk and save it in the cache
            node = FXMLLoader.load(getClass().getResource("/view/" + fxmlPath));
            viewCache.put(fxmlPath, node);
        }

        contentPane.getChildren().setAll(node); // This swaps the content instantly
        // Ensure the sub-view fills the whole pane
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}