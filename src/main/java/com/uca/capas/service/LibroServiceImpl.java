package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.dao.LibroDAO;
import com.uca.capas.domain.Libro;

@Service
public class LibroServiceImpl implements LibroService{
	
	@Autowired
	LibroDAO lDao;
	
	@Override
	public List<Libro> findAll() throws DataAccessException {
		return lDao.findAll();
	}

	@Override
	public void insert(Libro libro) throws DataAccessException {
		lDao.insert(libro);
	}
}
