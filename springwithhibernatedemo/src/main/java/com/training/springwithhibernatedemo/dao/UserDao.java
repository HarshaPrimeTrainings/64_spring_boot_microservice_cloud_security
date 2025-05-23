package com.training.springwithhibernatedemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.springwithhibernatedemo.User;

@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public String saveUser(User u) {
		Session session= sessionFactory.openSession();
		 Transaction tr= session.beginTransaction();
		 session.persist(u);
		 tr.commit();
		 session.close();
		 return "User Saved";
	}
	
	public List<User> getAllUsers(){
		Session session= sessionFactory.openSession();
		 List<User> users = session.createQuery("FROM User").list();
		 session.close();
		 return users;
	}
	
	public User getUserByid(Integer uid) {
		Session session= sessionFactory.openSession();
		 //Return data from Cache. If Object not found throw Nullpointer
		 //User u = session.load(User.class, uid);
		 
		 //Get the data from database, if Data not found return null
		 User u = session.get(User.class, uid);
		 session.close();
		 return u;
	}
	
	public String deleteUser(Integer uid) {
		Session session= sessionFactory.openSession();
		 Transaction tr= session.beginTransaction();
		 User u = getUserByid(uid);
		 session.delete(u);
		 tr.commit();
		 session.close();
		 return "user Deleted";
	}
	
	
}
