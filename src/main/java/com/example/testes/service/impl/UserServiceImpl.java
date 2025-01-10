package com.example.testes.service.impl;

import com.example.testes.domain.dto.UserDTO;
import com.example.testes.exceptions.ObjectNotFoundException;
import com.example.testes.model.User;
import com.example.testes.repository.UserRepository;
import com.example.testes.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;


    @Override
    public User findByiD(Long id) {
        Optional<User> obj = userRepository.findById(id);

        return obj.orElseThrow(()-> new ObjectNotFoundException("Não há registros para esse ID"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDto) {
        return userRepository.save(mapper.map(userDto, User.class));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(UserDTO userDTO) {
        return userRepository.save(mapper.map(userDTO, User.class));
    }


}
