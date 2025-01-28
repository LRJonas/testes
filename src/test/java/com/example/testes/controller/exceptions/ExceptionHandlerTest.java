package com.example.testes.controller.exceptions;

import com.example.testes.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExceptionHandlerTest {

    @InjectMocks
    private ExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp(){

    }


    @Test
    void whenObjectNotFoundThenReturnResponseEntity() {

        ResponseEntity<StandarError> response = exceptionHandler
                .objectNotFound(new ObjectNotFoundException("Não há registros para esse ID"), new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandarError.class, response.getBody().getClass());
        Assertions.assertEquals("Não há registros para esse ID", response.getBody().getError());
    }
}