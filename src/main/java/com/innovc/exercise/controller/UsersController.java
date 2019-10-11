package com.innovc.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovc.exercise.dto.UserDTO;
import com.innovc.exercise.exception.ConflictException;
import com.innovc.exercise.users.service.UserService;

@RestController
@RequestMapping("/innovc/exercise/v1")
public class UsersController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(
			value = "/users", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getAllUsers() {
		ResponseEntity<Object> response = null;
		try {
			List<UserDTO> listUsers = userService.getAllUsers();
			response = new ResponseEntity<Object>(listUsers, HttpStatus.OK);
		} catch (Exception e) {
			System.out.print(e);
			response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(
			value = "/users/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getUser(@PathVariable Integer id) {
		ResponseEntity<Object> response = null;
		try {
			UserDTO userDTO = userService.getUser(id);
			response = new ResponseEntity<Object>(userDTO, HttpStatus.OK);
		} catch (Exception e) {
			System.out.print(e);
			response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(
			value = "/users/create", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
		ResponseEntity<Object> response = null;
		try {
			userDTO = userService.saveUser(userDTO);
			response = new ResponseEntity<Object>(userDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.print(e);
			response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(
			value = "/users/{id}/update", 
			method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
		ResponseEntity<Object> response = null;
		try {
			userDTO.setId(id);
			userDTO = userService.saveUser(userDTO);
			response = new ResponseEntity<Object>(userDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.print(e);
			response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(
			value = "/users/{id}/delete", 
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> deleteUser(@PathVariable("id") Integer id) {
		ResponseEntity<Object> response = new ResponseEntity<Object>(HttpStatus.OK);
		try {
			userService.delete(id);
		} catch(EmptyResultDataAccessException e) {
			System.out.print(e);
			throw new ConflictException();
		}
		catch (Exception e) {
			System.out.print(e);
			response = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}
