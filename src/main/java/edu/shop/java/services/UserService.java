package edu.shop.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.shop.java.dao.UserDao;
import edu.shop.java.models.User;


@Service(value="userService")
public class UserService implements UserDetailsService{

	@Autowired()
	@Qualifier(value = "userFileDao")
	private UserDao userDao;

	public UserService() {

	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.getByUsername(username);
		
		if (user==null){
			throw new UsernameNotFoundException("Username with username: " + username + " not found!");
		}
		
		return user;
	}
}
