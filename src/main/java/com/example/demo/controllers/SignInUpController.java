package com.example.demo.controllers;

import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.models.User;
import com.example.demo.service.SignInUpService;
//import com.example.demo.translater.LgTranslator;
import com.example.demo.service.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/auth")
public class SignInUpController {
    @Autowired
    private SignInUpService service;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("user", new User());
        return "auth/sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDto form, Model model) {
        Status status = service.signUp(form);
        if (status.getStatus()) {
            return "redirect:/auth/signIn";
        } else {
            model.addAttribute("status", status);
            model.addAttribute("email", form.getEmail());
            model.addAttribute("password", form.getPassword());
            model.addAttribute("password_repeat", form.getPasswordRepeat());
            return "auth/sign_up";
        }

    }

    @GetMapping("/signIn")
    public String getSignInPage(Model model) {
        return "auth/sign_in";
    }

}
