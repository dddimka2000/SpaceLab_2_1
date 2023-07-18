package org.example.service;

import org.example.model.UserEntity;
import org.example.repository.SessionRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void loadUserByUsername() {
        String username = "w";
        UserEntity userEntity = new UserEntity();
        userEntity.setLog(username);
        when(userRepository.findFirstByLog(username)).thenReturn(Optional.of(userEntity));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        verify(userRepository).findFirstByLog(username);
    }

    @Test
    void findById() {
        UserEntity user = new UserEntity();
        Integer userId = 2;
        user.setIdUser(userId);
        user.setLog("newName");
        user.setGender(true);
        user.setPassword("1");
        userDetailsService.save(user);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<UserEntity> userEntitySQL = userDetailsService.findById(2);
        Assertions.assertEquals(user, userEntitySQL.get());
    }
    @Test
    void loadUserByUsername_InvalidUsername_ThrowsUsernameNotFoundException() {
        String username = "";
        when(userRepository.findFirstByLog(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(username));
        verify(userRepository).findFirstByLog(username);
    }

    @Test
    void delete() {

    }

    @Test
    void findByLog() {
        UserEntity user = new UserEntity();
        String userName = "newName";
        user.setIdUser(2);
        user.setLog(userName);
        user.setGender(true);
        user.setPassword("1");
        userDetailsService.save(user);
        when(userRepository.findFirstByLog(userName)).thenReturn(Optional.of(user));
        Optional<UserEntity> userEntitySQL = userDetailsService.findByLog(userName);
        Assertions.assertEquals(user, userEntitySQL.get());
    }

    @Test
    void findAllPage() {
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());
        userList.add(new UserEntity());
        Page<UserEntity> expectedPage = new PageImpl<>(userList, pageable, userList.size());

        when(userRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<UserEntity> resultPage = userDetailsService.findAllPage(pageNumber, pageSize);

        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);
        verify(userRepository).findAll(pageable);

    }

    @Test
    void save() {
        UserEntity userEntity = new UserEntity();
        userDetailsService.save(userEntity);
        verify(userRepository).save(userEntity);
    }

    @Test
    void findAll() {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(new UserEntity());
        userList.add(new UserEntity());
        userRepository.save(userList.get(0));
        userRepository.save(userList.get(1));
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userRepository.findAll(), userList);
    }
}