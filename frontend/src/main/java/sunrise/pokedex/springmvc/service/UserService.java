package sunrise.pokedex.springmvc.service;

import java.util.List;

import sunrise.pokedex.springmvc.model.User;

public interface UserService {

	User findById(Long id);

	User findByUsername(String username);

	User saveUser(User user);

	User updateUser(User user);

	Long deleteUserById(Long id);

	List<User> findAllUsers();

	List<User> deleteAllUsers();

	public Boolean isUserExist(User user);

}
