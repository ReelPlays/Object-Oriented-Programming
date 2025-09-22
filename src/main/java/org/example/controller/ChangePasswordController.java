package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.example.dao.UserDAO;
import org.example.model.User;

public class ChangePasswordController implements UserAwareController {
    @FXML private PasswordField txtNewPassword;
    @FXML private PasswordField txtConfirmNewPassword;
    @FXML private Label lblStatus;

    private final UserDAO userDAO = new UserDAO();
    private User loggedInUser;

    @Override
    public void setUser(User user) {
        this.loggedInUser = user;
    }

    @FXML
    private void handleChangePassword() {
        if (loggedInUser == null) {
            lblStatus.setText("Error: No logged-in user!");
            return;
        }

        String newPassword = txtNewPassword.getText();
        String confirmNewPassword = txtConfirmNewPassword.getText();

        if (newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            lblStatus.setText("Please fill in all fields.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            lblStatus.setText("New password and confirmation do not match.");
            return;
        }

        boolean updated = userDAO.changePassword(loggedInUser.getId(), newPassword);
        if (updated) {
            lblStatus.setText("Password updated successfully!");
            // Optionally close the window or clear fields
        } else {
            lblStatus.setText("Failed to update password.");
        }
    }
}
