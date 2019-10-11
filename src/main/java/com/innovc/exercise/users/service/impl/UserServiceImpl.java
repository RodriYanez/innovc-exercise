package com.innovc.exercise.users.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.innovc.exercise.commons.service.impl.CRUDGenericServiceImpl;
import com.innovc.exercise.dao.UsersDao;
import com.innovc.exercise.dto.UserDTO;
import com.innovc.exercise.model.User;
import com.innovc.exercise.users.service.UserService;

@Service
public class UserServiceImpl extends CRUDGenericServiceImpl<User, Integer> implements UserService {

	@Autowired
	private UsersDao usersDao;
	
	@Override
	public CrudRepository<User, Integer> getDao() {
		return usersDao;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) throws Exception {
		User user = new User();
		try {
			if(userDTO.getId() != null) {
				user = get(userDTO.getId()); //If id is null then create else update.
			} 
			user.setName(userDTO.getName() != null ? userDTO.getName() : user.getName());
			if(userDTO.getBirthdate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
				user.setBirthDate(sdf.parse(userDTO.getBirthdate()));
			}
			
			user = save(user);
		} catch(Exception e) {
			throw e;
		}
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() throws Exception {
		List<UserDTO> listUsersDTO = new ArrayList<UserDTO>();
		try {
			List<User> listUsers = getAll();
			for(User user : listUsers) {
				UserDTO userDTO = mapUserToUserDTO(user);
				listUsersDTO.add(userDTO);
			}
		} catch(Exception e) {
			throw e;
		}
		return listUsersDTO;
	}

	@Override
	public UserDTO getUser(Integer id) throws Exception {
		UserDTO userDTO = null;
		try {
			User user = get(id);
			userDTO = mapUserToUserDTO(user);
		} catch(Exception e) {
			throw e;
		}
		return userDTO;
	}
	
	private UserDTO mapUserToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		if(user.getBirthDate() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
			userDTO.setBirthdate(sdf.format(user.getBirthDate()));
		}
		return userDTO;
	}
}
