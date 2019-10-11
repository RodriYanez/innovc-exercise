package com.innovc.exercise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.innovc.exercise.model.User;

@Component
public interface UsersDao extends JpaRepository<User, Integer>{

}
