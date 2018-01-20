package dao;

import org.hibernate.Session;

public class Dao<T> 
{
	private Class<T> persistentClass;
	
	private Session session;
	
	public Dao(Session session, Class<T> persistentClass)
	{
		this.session= session;
		this.persistentClass= persistentClass;
	}
	
	public T load(int id)
	{
		return (T) session.load(persistentClass, id);
	}
	public void save(T t)
	{
		session.save(t);
		org.hibernate.Transaction tx = session.beginTransaction();
		tx.commit();
	}

}
