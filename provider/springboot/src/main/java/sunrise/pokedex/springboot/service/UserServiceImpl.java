package sunrise.pokedex.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sunrise.pokedex.springboot.dao.UserDao;
import sunrise.pokedex.springboot.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User findById(Long id) {
		if (Optional.ofNullable(id).isPresent()) {
			return userDao.findById(id);
		} else {
			throw new RuntimeException();
		}
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Transactional
	public User saveUser(User user) {
		if (user != null) {
			return userDao.saveUser(user);	
		} else {
			throw new RuntimeException();
		}
	}

	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Transactional
	public Long deleteUserById(Long id) {
		if (Optional.ofNullable(id).isPresent()) {
			return userDao.deleteUserById(id);
		} else {
			throw new RuntimeException();
		}
	}

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Transactional
	public List<User> deleteAllUsers() {
		return userDao.deleteAllUsers();
	}

	public Boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}


}
