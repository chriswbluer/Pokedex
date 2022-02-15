package sunrise.pokedex.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import sunrise.pokedex.springboot.manager.UserManager;
import sunrise.pokedex.springboot.model.User;
import sunrise.pokedex.springboot.view.UserViewImpl;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    // -------------------Retrieve All Users--

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        List<UserViewImpl> users = userManager.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<UserViewImpl>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserViewImpl>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        logger.debug("Fetching User with id " + id);
        UserViewImpl user = userManager.findById(id);
        if (user == null) {
            logger.debug("User with id " + id + " not found");
            return new ResponseEntity<UserViewImpl>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UserViewImpl>(user, HttpStatus.OK);
    }

    // -------------------Create a User--

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserViewImpl user, UriComponentsBuilder ucBuilder) {
        logger.debug("Creating User " + user.getUsername());

        if (userManager.isUserExist(user)) {
            logger.debug("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        UserViewImpl userViewImpl = userManager.saveUser(user);

        // HttpHeaders headers = new HttpHeaders();
        // headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<UserViewImpl>(userViewImpl, HttpStatus.CREATED);
    }

    // ------------------- Update a User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.debug("Updating User " + id);

        UserViewImpl currentUser = userManager.findById(id);

        if (currentUser == null) {
            logger.debug("User with id " + id + " not found");
            return new ResponseEntity<UserViewImpl>(HttpStatus.NO_CONTENT);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());

        userManager.updateUser(currentUser);
        return new ResponseEntity<UserViewImpl>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a User--

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        logger.debug("Fetching & Deleting User with id " + id);

        UserViewImpl user = userManager.findById(id);
        if (user == null) {
            logger.debug("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }

        userManager.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users--

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUsers() {
        logger.debug("Deleting All Users");

        userManager.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
