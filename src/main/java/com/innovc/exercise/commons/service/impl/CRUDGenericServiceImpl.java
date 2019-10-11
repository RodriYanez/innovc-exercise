package com.innovc.exercise.commons.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.innovc.exercise.commons.service.CRUDGenericService;

@Service
public abstract class CRUDGenericServiceImpl<T, ID extends Serializable> implements CRUDGenericService<T, ID> {

	@Override
	public List<T> getAll() {
		List<T> returnList = new ArrayList<>();
		List<T> response = (List<T>) getDao().findAll();
		if(response != null) {
			response.forEach(optional -> returnList.add(optional));
		}
		return returnList;
	}

	@Override
	public T get(ID id) {
		Optional<T> optional = getDao().findById(id);
		return optional != null ? optional.get() : null;
	}

	@Override
	public T save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(ID id) {
		getDao().deleteById(id);
	}
	
	public abstract CrudRepository<T, ID> getDao();

}
