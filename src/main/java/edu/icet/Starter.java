package edu.icet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
        stage.setTitle("NexTrade POS - Global Essential Supply Chain");

        // THIS LINE: Sets the icon for the taskbar and title bar
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/nextradelogobgrem.png")));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
