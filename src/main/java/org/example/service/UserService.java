package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void addUser(String name) {
        System.out.println("Adding user: " + name);
    }

    public void deleteUser(String name) {
        System.out.println("Deleting user: " + name);
    }
}