package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.dao.UserDAO;
import org.example.model.User;

public class RegisterController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private Label lblStatus;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleRegister() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String confirmPassword = txtConfirmPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            lblStatus.setText("Please enter username, password & confirm password.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            lblStatus.setText("Passwords do not match!");
            return;
        }

        boolean success = userDAO.register(username, password);
        if (success) {
            lblStatus.setText("Registration successful! Redirecting...");

            // fetch the new user from DB
            User newUser = userDAO.login(username, password);

            if (newUser != null) {
                // close register window
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                stage.close();

                // open dashboard
                try {
                    SceneLoader.load("dashboard.fxml", "Dashboard", newUser);
                } catch (Exception e) {
                    e.printStackTrace();
                    lblStatus.setText("Error loading dashboard: " + e.getMessage());
                }
            }
        } else {
            lblStatus.setText("Username already exists!");
        }
    }
}
