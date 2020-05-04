package com.rest.webservices.restfulwebservice.user;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static int counter;
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(++counter,"Jagan", new Date()));
		System.out.println("Counter Value"+counter);
		users.add(new User(++counter,"Padma Jyothi", new Date()));
		users.add(new User(++counter,"Danush", new Date()));
		users.add(new User(++counter,"Disha", new Date()));
		users.add(new User(++counter,"KJM", new Date()));
		users.add(new User(++counter,"PJ", new Date()));
		
	}
	
	public User getUser(int num) {
		for (User user:users) {
			if (user.getId() == num) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator<User> iterator = users.iterator();
		
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++counter);
		}
		 users.add(user);
		 return user;
	}
	

}
