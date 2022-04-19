package com.example.integrador.service;


import com.example.integrador.entities.AppUser;
import com.example.integrador.entities.AppUsuarioRoles;
import com.example.integrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String pass= passwordEncoder.encode("adminpass");
        String pass2= passwordEncoder.encode("userpass");


        userRepository.save(new AppUser ("Admin","Admin2022","admin@gmail.com",pass, AppUsuarioRoles.ROLE_ADMIN));
        userRepository.save(new AppUser ("User","User2022","user@gmail.com",pass2, AppUsuarioRoles.ROLE_USER));


    }
}
