package com.project.bms.controller;

import com.project.bms.model.User;
import com.project.bms.repository.UserRepository;
import com.project.bms.controller.LoginController;
import com.project.bms.service.ConnectDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.sql.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository){
        this.userRepository = userRepository;
}
    @PostMapping(value = "/req/signup", consumes = "application/json")
//    public ResponseEntity<String> createUser(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully. Redirecting to login...");
//
//    }
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String db_url = "jdbc:mariadb://localhost:3306/registration";
        String db_user = "root";
        String db_pass = "MYSql@123";

        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into user(username, password, )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();


        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully. Redirecting to login...");

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
