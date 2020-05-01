package com.example.demo.service;

import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserDaoImpl;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
//import com.example.demo.dto.*;

@Component
public class SignInUpServiceImpl implements SignInUpService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void signUp(SignUpDto form) {
        System.out.println("fff" + form.getEmail() + "\n" + form.getPassword() + "\n" + form.getPasswordRepeat());
        System.out.println(passwordEncoder.encode(form.getPassword()));
        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .passwordRepeat(passwordEncoder.encode(form.getPasswordRepeat()))
                .role(Role.USER)
                .build();

//        User user = new User(form.getLogin(), )

//         usersRepository.save(user);
        userDao.saveUser(user);
    }

//    @Override
//    public void signIn(SignInDto form) {
//        Optional<User> user = usersRepository.findByEmail(form.getEmail());
//        System.out.println("user" + user.isPresent() + " " + user);
//        if (user.isPresent()) {
//            User user1 = user.get();
//            if (user1.getPassword().equals(form.getPassword())) {
//
//            }
//        }
//    }
}

