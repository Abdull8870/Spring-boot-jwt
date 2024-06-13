package com.ltp.gradesubmission.web;

import javax.validation.Valid;

import com.ltp.gradesubmission.dataDTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<String> findById(@PathVariable Long id) {
		String user=userService.getUser(id).getUsername();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

    @PostMapping("/register")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
		System.out.println("Inside create user");
		return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
	}

}
