package com.example.demo.security;

import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = usersRepository.findUserByEmail(s);
        System.out.println(s + " userOptional" + userOptional.isPresent() + userOptional);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        } throw new UsernameNotFoundException("User not Found");
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userOptional = usersRepository.findUserByEmail(email);
//        System.out.println(email + " userOptional" + userOptional.isPresent() + userOptional);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return new UserDetailsImpl(user);
//        } throw new UsernameNotFoundException("User not Found");
//    }
}
