package com.sparksupport.sparktraining.services;

import com.sparksupport.sparktraining.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorService authorService;

    @Test
    void deleteAuthorById(){

        doNothing().when(authorRepository).deleteById(1);

        authorService.deleteById(1);

        verify(authorRepository, times(1)).deleteById(1);

    }
}