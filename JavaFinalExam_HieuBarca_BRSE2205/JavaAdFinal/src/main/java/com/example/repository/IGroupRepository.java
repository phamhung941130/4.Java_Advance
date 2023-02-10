package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entyti.Group;

public interface IGroupRepository extends JpaRepository<Group, Integer> {

}
