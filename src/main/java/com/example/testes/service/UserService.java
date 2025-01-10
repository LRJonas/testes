package com.example.testes.service;

import com.example.testes.domain.dto.UserDTO;
import com.example.testes.model.User;

import java.util.List;

public interface UserService {

    User findByiD(Long id);

    List<User> findAll();

    User create(UserDTO userDto);

    void delete(Long id);

    User update(UserDTO userDTO);
}
