package com.example.sec.controllers;


import com.example.sec.DAO.UserDAO;

import com.example.sec.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class mainController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder; //Шифрування паролю

    @PostMapping("/add")
    public void add(@RequestBody String user) throws IOException {
        User newUser = new ObjectMapper().readValue(user, User.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userDAO.save(newUser);
    }

    @GetMapping("/show")
    public String show() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(userDAO.findAll()));
        return new ObjectMapper().writeValueAsString(userDAO.findAll());
    }

}
