package com.ltp.gradesubmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.ltp.gradesubmission.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {


//	User findByUsername(String username);

	Optional<User> findByUsername(String username);

}