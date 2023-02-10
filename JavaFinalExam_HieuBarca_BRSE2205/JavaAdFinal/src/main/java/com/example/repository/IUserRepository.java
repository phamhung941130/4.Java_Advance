package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entyti.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	User findByUserNameAndPassword(String userName, String password);
}
