package org.example.controller;

import org.example.model.User;

public interface UserAwareController {
    void setUser(User user);
}
