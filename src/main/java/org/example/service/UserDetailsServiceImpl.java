package org.example.service;


import lombok.extern.log4j.Log4j2;
import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.example.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            log.error("Log empty!");
        }
        System.out.println(username);
        Optional<UserEntity> userEntity = userRepository.findFirstByLog(username);
        System.out.println(userEntity);
        if (userEntity.isEmpty())
            throw new UsernameNotFoundException("User not found!");


        return new UserDetailsImpl (userEntity.get());
    }

    public Optional<UserEntity> findById(Integer integer){
        return userRepository.findById(integer);
    }
    public void delete(UserEntity integer){
        userRepository.delete(integer);
    }
    public Optional<UserEntity> findByLog(String login){
        return userRepository.findFirstByLog(login);
    }

    public Page<UserEntity> findAllPage(Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);

    }

    @Transactional
    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
        log.info(userEntity);
    }
    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }
}
