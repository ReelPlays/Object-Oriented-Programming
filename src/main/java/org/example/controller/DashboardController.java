package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.model.User;

public class DashboardController implements UserAwareController {
    private User loggedInUser;

    @FXML private Label lblWelcome;

    @Override
    public void setUser(User user) {
        this.loggedInUser = user;
        if (lblWelcome != null) {
            lblWelcome.setText("Welcome, " + user.getUsername() + "!");
        }
    }

    @FXML
    private void handleChangePassword() {
        SceneLoader.load("change_password.fxml", "Change Password", loggedInUser);
    }
}
