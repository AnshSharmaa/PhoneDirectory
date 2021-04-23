package com.project.PhoneDir.service;

import com.project.PhoneDir.model.User;
import com.project.PhoneDir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Contain all the business logic and communicate to db
    public User login(User user) {
        User existingUser = userRepository.checkCredentials(user.getUsername());
        if(existingUser!=null){
            if(!passwordEncoder.matches(user.getPassword(),existingUser.getPassword())) {
                return null;
            } else {
                return existingUser;
            }
        } else return null;
    }

    public boolean registerUser(User userToEncrpyt) {

        User newUser = new User();
        newUser.setUsername(userToEncrpyt.getUsername());
        newUser.setEmail(userToEncrpyt.getEmail());
        newUser.setPhonenumber(userToEncrpyt.getPhonenumber());
        newUser.setPassword(passwordEncoder.encode(userToEncrpyt.getPassword()));
        return userRepository.registerUser(newUser);
    }
}
