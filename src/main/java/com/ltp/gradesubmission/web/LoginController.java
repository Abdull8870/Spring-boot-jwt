package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController()
@RequestMapping("/authenticate")
public class LoginController {

    @PostMapping()
    public String login(@RequestBody User user){
        System.out.println(user);
        return "you're logged in successfully";
    }
}
