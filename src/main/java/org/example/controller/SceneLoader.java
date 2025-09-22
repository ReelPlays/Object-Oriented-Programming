package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.User;

public class SceneLoader {

    public static void load(String fxmlFile, String title, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource("/" + fxmlFile));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof UserAwareController userAwareController && user != null) {
                userAwareController.setUser(user);
            }

            Stage stage = new Stage();
            stage.setTitle(title);
            Scene scene = new Scene(root);
        scene.getStylesheets().add(SceneLoader.class.getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
