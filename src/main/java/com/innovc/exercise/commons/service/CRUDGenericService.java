package com.innovc.exercise.commons.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CRUDGenericService<T, ID extends Serializable> {

	List<T> getAll();
	T get(ID id);
	T save(T entity);
	void delete(ID id);
}
