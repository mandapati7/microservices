package com.testcom.oce.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.testcom.oce.vo.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1, "Gopikrishna Mandapati", new Date()));
		users.add(new User(2, "Lakshmi Bezawada", new Date()));
		users.add(new User(3, "Spoorthi Mandapati", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User saveUser(User user) {
		users.add(user);
		return user;
	}
	
	public User findUserById(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUserById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
