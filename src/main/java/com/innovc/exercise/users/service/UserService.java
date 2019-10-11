package com.innovc.exercise.users.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.innovc.exercise.commons.service.CRUDGenericService;
import com.innovc.exercise.dto.UserDTO;
import com.innovc.exercise.model.User;

@Service
public interface UserService extends CRUDGenericService<User, Integer> {

	public List<UserDTO> getAllUsers() throws Exception;
	public UserDTO getUser(Integer id) throws Exception;
	public UserDTO saveUser(UserDTO userDTO) throws Exception;
	
}
