package com.example.testes.service;

import com.example.testes.model.User;
import com.example.testes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Transactional
        public User create(User user) {
            User userCreated = userRepository.save(user);
            return userCreated;
        }

        @Transactional(readOnly = true)
        public User findById(Long id) {
            User userFound =  userRepository.findById(id).orElse(null);
            System.out.println(userFound);
            return userFound;
        }

        @Transactional
        public List<User> findAll(){
            List<User> users = userRepository.findAll();
            return users;
        }
    }
