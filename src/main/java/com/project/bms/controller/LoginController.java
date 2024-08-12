package com.project.bms.controller;

import com.project.bms.service.ConnectDB;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


@RestController
public class LoginController {

    @PostMapping("/req/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            ConnectDB connectDB = new ConnectDB();
            Connection connection = connectDB.getConnection();
            Statement statement = connection.createStatement();
            String query = "select username, password from users where username = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (!(resultSet.getString("username") == username && resultSet.getString("password") == password)) {
                    return "redirect:/login";
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/index";

    }

}
