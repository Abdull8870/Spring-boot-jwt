package com.ltp.gradesubmission.service;

import java.util.Optional;

import com.ltp.gradesubmission.dataDTO.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.exception.EntityNotFoundException;
import com.ltp.gradesubmission.repository.UserRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public UserDTO saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        UserDTO userDTO=UserDTO.
                builder().
                username(save.getUsername()).
                Id(save.getId()).build();
         return userDTO;
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return this.unwrapUser(user,404L);
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
    
}
