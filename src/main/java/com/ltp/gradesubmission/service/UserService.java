package com.ltp.gradesubmission.service;


import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.userDTO.UserDTO;

public interface UserService {
    User getUser(Long id);
    UserDTO saveUser(User user);
}