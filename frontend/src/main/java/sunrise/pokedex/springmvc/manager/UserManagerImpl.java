package sunrise.pokedex.springmvc.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sunrise.pokedex.springmvc.model.User;
import sunrise.pokedex.springmvc.service.UserService;
import sunrise.pokedex.springmvc.view.UserViewImpl;

@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserService userService;

    public UserViewImpl convertToView(User user) {
		UserViewImpl userView = new UserViewImpl();
		BeanUtils.copyProperties(user, userView);
		return userView;
	}
	
	public User convertToImpl(UserViewImpl user) {
		User userImpl = new User();
		BeanUtils.copyProperties(user, userImpl);
		return userImpl;
	}

    @Override
    public UserViewImpl findById(Long id) {
        User userImpl = userService.findById(id);
        UserViewImpl userView = convertToView(userImpl);
        return userView;
    }

    @Override
    public UserViewImpl findByUsername(String username) {
        User userImpl = userService.findByUsername(username);
        UserViewImpl userView = convertToView(userImpl);
        return userView;
    }

    @Override
    public UserViewImpl saveUser(UserViewImpl user) {
        User userImpl = convertToImpl(user);
		User serviceUserImpl = userService.saveUser(userImpl);
		UserViewImpl userView = convertToView(serviceUserImpl);
		return userView;
    }

    @Override
    public UserViewImpl updateUser(UserViewImpl user) {
        User userImpl = convertToImpl(user);
		User updatedUserImpl = userService.updateUser(userImpl);
		UserViewImpl userView = convertToView(updatedUserImpl);
		return userView;
    }

    @Override
    public Long deleteUserById(Long id) {
        return userService.deleteUserById(id);
    }

    @Override
    public List<UserViewImpl> findAllUsers() {
        List<User> userList = userService.findAllUsers();
		List<UserViewImpl> userViewList = new ArrayList<UserViewImpl>();
		for (User user : userList) {
			UserViewImpl userView = new UserViewImpl();
			BeanUtils.copyProperties(user, userView);
			userViewList.add(userView);
		}
		return userViewList;
    }

    @Override
    public List<User> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @Override
    public Boolean isUserExist(UserViewImpl user) {
        User userImpl = convertToImpl(user);
        return userService.isUserExist(userImpl);
    }

    @Override
    public User findByName(String username) {
        return userService.findByUsername(username);
    }
    
}
