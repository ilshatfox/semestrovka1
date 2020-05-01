package com.example.demo.service;


import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SignUpDto;

public interface SignInUpService {
    Status signUp(SignUpDto form);

//    void signIn(SignInDto form);
}

