package com.example.testes.service.impl;

import com.example.testes.domain.dto.UserDTO;
import com.example.testes.exceptions.ObjectNotFoundException;
import com.example.testes.model.User;
import com.example.testes.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    public static final long ID = 10L;
    public static final String NAME = "kiko";
    public static final String EMAIL = "kiko@zinho.com";
    public static final String PASSWORD = "123456";


    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDTO userDTO;

    private Optional<User> optional;

    private List<UserDTO> list = new ArrayList<>();

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByiDReturnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optional);

        User response = service.findByiD(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
    }

    @Test
    void whenFindByIdRetunrObjectNotFoundException(){
        Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException("Não há registros para esse ID"));

        try{
            service.findByiD(2L);
        }catch (Exception e){
            Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
            Assertions.assertEquals("Não há registros para esse ID", e.getMessage());
        }


    }

    @Test
    void whenFindAllReturnListUser() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<User> list = service.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1, list.size());

        Assertions.assertEquals(ID, list.get(0).getId());
        Assertions.assertEquals(NAME, list.get(0).getName());
    }

    @Test
    void whenCreateReturnSucess() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(user, response);
        Assertions.assertEquals(user.getClass(), response.getClass());
    }

    @Test
    void deleteWhenSucess() {
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.doNothing().when(repository).deleteById(Mockito.anyLong());
        service.delete(ID);
        Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    void whenUpdateReturnSucess() {

        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.update(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(user, response);
        Assertions.assertEquals(user.getClass(), response.getClass());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException(){

        Mockito.when(repository.findById(Mockito.anyLong())).thenThrow(new ObjectNotFoundException("Não há registros para esse ID"));

        try{
            service.delete(ID);
        } catch (Exception e) {
            Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}