package sunrise.pokedex.springmvc.manager;

import java.util.List;

import sunrise.pokedex.springmvc.model.User;
import sunrise.pokedex.springmvc.view.UserViewImpl;

public interface UserManager {
    UserViewImpl findById(Long id);

	UserViewImpl findByUsername(String username);

	UserViewImpl saveUser(UserViewImpl user);

	UserViewImpl updateUser(UserViewImpl user);

	Long deleteUserById(Long id);

	List<UserViewImpl> findAllUsers();

	List<User> deleteAllUsers();

	public Boolean isUserExist(UserViewImpl user);

	User findByName (String username);
}
