package com.innovc.exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.innovc.exercise.dao.UsersDao;
import com.innovc.exercise.dto.UserDTO;
import com.innovc.exercise.model.User;
import com.innovc.exercise.users.service.impl.UserServiceImpl;

public class UserServiceTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UsersDao usersDao;
	
	@Mock
	User user;
	
	@Mock
	UserDTO userDTO;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		assertNotNull(userServiceImpl);
		assertNotNull(usersDao);
		assertNotNull(user);
		assertNotNull(userDTO);

		when(userServiceImpl.getDao().findById(anyInt())).thenReturn(Optional.of(user));
	}

	@Test
	public void getAllUsersTest() throws Exception {
		int listSize = 3;
		
		List<User> listUsers = new ArrayList<>();
		for(int i = 0; i < listSize; i++) {
			listUsers.add(user);
		}
		
		assertNotNull(listUsers);
		
		when(userServiceImpl.getAll()).thenReturn(listUsers);
		List<UserDTO> listUsersResponse = userServiceImpl.getAllUsers();
		
		assertNotNull(listUsersResponse);
		assertEquals(listSize, listUsersResponse.size());
	}
	
	@Test
	public void getUserTest() throws Exception {
		UserDTO response = userServiceImpl.getUser(user.getId());
		assertNotNull(response);
	}
	
	@Test 
	public void saveUserTest() throws Exception {
		when(userDTO.getId()).thenReturn(null);
		when(userServiceImpl.save(any(User.class))).thenReturn(user);
		UserDTO response = userServiceImpl.saveUser(userDTO);
		assertNotNull(response);
	}
	
	@Test
	public void updateUserTest() throws Exception {
		when(userServiceImpl.save(any(User.class))).thenReturn(user);
		UserDTO response = userServiceImpl.saveUser(userDTO);
		assertNotNull(response);
	}
}
