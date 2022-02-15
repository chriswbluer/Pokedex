package sunrise.pokedex.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sunrise.pokedex.springmvc.dao.UserDao;
import sunrise.pokedex.springmvc.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User findById(Long id) {
		return userDao.findById(id);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Transactional
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Transactional
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Transactional
	public Long deleteUserById(Long id) {
		return userDao.deleteUserById(id);
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
