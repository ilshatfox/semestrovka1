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

import java.nio.file.FileAlreadyExistsException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public Status signUp(SignUpDto form) {
        Optional<User> oldUser = usersRepository.findUserByEmail(form.getEmail());
        if (!oldUser.isPresent()) {
            Pattern pattern = Pattern.compile("[^@\\.]*?@[^\\.]*?\\.[^\\.]*?");
            Matcher matcher = pattern.matcher(form.getEmail());
            if (!matcher.find()) {
                return new Status(false, "Email неверный!");
            }
            else if (form.getPasswordRepeat().equals(form.getPassword())) {
                User user = User.builder()
                        .email(form.getEmail())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .passwordRepeat(passwordEncoder.encode(form.getPasswordRepeat()))
                        .role(Role.USER)
                        .build();
                userDao.saveUser(user);
                return new Status(true, "Успешно зарегистрирован!");
            } else {
                return new Status(false,"Пароли не совпадают!");
            }

        } else {
            return new Status(false,"Такой email уже используется!");
        }

    }
}

