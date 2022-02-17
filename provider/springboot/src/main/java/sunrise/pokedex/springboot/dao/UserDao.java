package sunrise.pokedex.springboot.dao;

import java.util.List;

import sunrise.pokedex.springboot.model.User;

public interface UserDao {

    User findById(Long id);

	User findByUsername(String username);

	User saveUser(User user);

	User updateUser(User user);

	Long deleteUserById(Long id);

	List<User> findAllUsers();

	List<User> deleteAllUsers();

	public Boolean isUserExist(User user);
    
}
