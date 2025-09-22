package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.dao.UserDAO;
import org.example.model.User;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMessage;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        User user = userDAO.login(txtUsername.getText(), txtPassword.getText());
        if (user != null) {
            lblMessage.setText("Login successful. Redirecting...");

            // Close login window
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.close();

            // Open dashboard
            try {
                SceneLoader.load("dashboard.fxml", "Dashboard", user);
            } catch (Exception e) {
                e.printStackTrace();
                lblMessage.setText("Error loading dashboard: " + e.getMessage());
            }
        } else {
            lblMessage.setText("Invalid login!");
        }
    }

    @FXML
    private void handleRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/register.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 300, 250));
            stage.initModality(Modality.APPLICATION_MODAL); // block login until closed
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
