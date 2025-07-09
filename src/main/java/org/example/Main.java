package org.example;

import config.AppConfig;
import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        System.out.println("Calling addUser...");
        userService.addUser("Alice");

        System.out.println("\nCalling deleteUser...");
        userService.deleteUser("Bob");
    }
}
