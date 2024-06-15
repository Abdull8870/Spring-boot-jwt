package com.ltp.gradesubmission.service;


import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.dataDTO.UserDTO;

import java.util.Optional;

public interface UserService {
    User getUser(Long id);
    UserDTO saveUser(User user);
    User getUser(String username);
}