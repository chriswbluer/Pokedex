package sunrise.pokedex.springmvc.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sunrise.pokedex.springmvc.model.User;
import sunrise.pokedex.springmvc.view.UserViewImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;

@Service
@PropertySource(value = { "classpath:application.properties" })
public class UserManagerImpl implements UserManager {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${pokedex.user.provider.url}")
    private String providerUrl;

    private static Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

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
        User userImpl = restTemplate.getForObject(providerUrl + id, User.class);
        UserViewImpl userView = convertToView(userImpl);
        return userView;
    }

    @Override
    public UserViewImpl findByUsername(String username) {
        User userImpl = restTemplate.getForObject(providerUrl + username, User.class);
        UserViewImpl userView = convertToView(userImpl);
        return userView;
    }

    @Override
    public UserViewImpl saveUser(UserViewImpl user) {
        User userImpl = convertToImpl(user);
        // TODO: restTemplate.exchange is better. More configuration options.
        User serviceUserImpl = restTemplate.postForEntity(providerUrl, new HttpEntity(userImpl), User.class)
                .getBody();
        UserViewImpl userView = convertToView(serviceUserImpl);
        return userView;
    }

    @Override
    public UserViewImpl updateUser(UserViewImpl user) {
        User userImpl = convertToImpl(user);
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", Long.toString(userImpl.getId()));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<User>(userImpl, headers);
        User updatedUserImpl = restTemplate.exchange(providerUrl + "/{id}", HttpMethod.PUT,
        requestEntity, User.class, params).getBody();
        UserViewImpl userView = convertToView(updatedUserImpl);
        return userView;
    }

    @Override
    public Long deleteUserById(Long id) {
        try {
            restTemplate.delete(providerUrl + id);
        } catch (Exception e) {
            logger.debug("Something went wrong. Error: " + e);
        }
        return id;
    }

    @Override
    public List<UserViewImpl> findAllUsers() {
        // TODO: restTemplate.exchange is better. More configuration options.
        User[] userList = restTemplate.getForEntity(providerUrl, User[].class).getBody();
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
        try {
            restTemplate.delete(providerUrl);
        } catch (Exception e) {
            logger.debug("Something went wrong. Error: " + e);
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean isUserExist(UserViewImpl user) {
        User userImpl = convertToImpl(user);
        return userImpl != null && userImpl.getId() != null && findById(userImpl.getId()) != null;
    }

    @Override
    public User findByName(String username) {
        return restTemplate.getForObject(providerUrl + username, User.class);
    }

}
