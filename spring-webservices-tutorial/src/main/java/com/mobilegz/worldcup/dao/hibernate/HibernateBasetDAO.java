package com.mobilegz.worldcup.dao.hibernate;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.mobilegz.worldcup.dao.BaseDAO;

@Repository
public class HibernateBasetDAO implements BaseDAO {

	@Autowired
	@Qualifier("sqliteSessionFactory")
	private SessionFactory sessionFactory;

	public <T> Serializable save(T entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}

	public <T> void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	public <T> void delete(T object) {
		sessionFactory.getCurrentSession().delete(object);
	}

}
