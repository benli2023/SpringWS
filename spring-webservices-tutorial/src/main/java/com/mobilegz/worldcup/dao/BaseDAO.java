package com.mobilegz.worldcup.dao;

import java.io.Serializable;

public interface BaseDAO {

	public abstract <T> Serializable save(T entity);

	public abstract <T> void update(T entity);

	public abstract <T> T get(Class<T> clazz, Serializable id);

	public abstract <T> void delete(T object);

}