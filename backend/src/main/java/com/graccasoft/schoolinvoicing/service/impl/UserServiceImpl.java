package com.graccasoft.schoolinvoicing.service.impl;

import com.graccasoft.schoolinvoicing.model.User;
import com.graccasoft.schoolinvoicing.repository.UserRepository;
import com.graccasoft.schoolinvoicing.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        userRepository.save(user);
    }
}
