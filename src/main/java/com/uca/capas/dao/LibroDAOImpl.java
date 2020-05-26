package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Libro;

@Repository
public class LibroDAOImpl implements LibroDAO{
	
	@PersistenceContext(unitName="capas")
	private EntityManager em;
	
	@Override
	public List<Libro> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_libro");
		Query query = em.createNativeQuery(sb.toString(), Libro.class);
		List<Libro> res = query.getResultList();
		return res;
	}

	@Override
	@Transactional
	public void insert(Libro libro) throws DataAccessException {
		em.persist(libro);
	}
}
