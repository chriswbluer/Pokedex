package sunrise.pokedex.springmvc.service;

import java.util.List;

import sunrise.pokedex.springmvc.model.User;

public interface UserService {

	User findById(long id);

	User findByName(String name);

	User saveUser(User user);

	User updateUser(User user);

	Long deleteUserById(long id);

	List<User> findAllUsers();

	List<User> deleteAllUsers();

	public boolean isUserExist(User user);

}
