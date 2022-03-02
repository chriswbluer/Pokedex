package sunrise.pokedex.springboot;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sunrise.pokedex.springboot.dao.UserDao;
import sunrise.pokedex.springboot.model.User;
import sunrise.pokedex.springboot.service.UserServiceImpl;

import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mockito;

@SpringBootTest
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    private User user = new User();

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveMockUserWithValidParametersAndVerifyItWasSaved() {
        user = buildMockUserDao();
        when(userServiceImpl.saveUser(user)).thenReturn(user);
        userServiceImpl.saveUser(user);
        Mockito.verify(userDao).saveUser(Mockito.any(User.class));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void attemptToSaveMockNullUserAndVerifyItWasNotSaved() {
        User User = null;
        userServiceImpl.saveUser(User);
        Mockito.verify(userDao, Mockito.times(0)).saveUser(Mockito.any(User.class));
    }

    @Test
    public void getUserWithValidId() {
        User User = buildMockUserDao();
        when(userServiceImpl.findById((long) 1)).thenReturn(User);
        User fromService = userServiceImpl.findById((long) 1);
        Assert.assertEquals(User, fromService);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throwRuntimeExceptionWhenAttemptingToGetUserWithNullId() {
        User User = buildMockUserDao();
        User.setId(null);
        userServiceImpl.findById(User.getId());
        Mockito.verify(userDao, Mockito.times(0)).findById(User.getId());
    }

    @Test
    public void getUserWithValidName() {
        User User = buildMockUserDao();
        when(userServiceImpl.findByUsername(User.getUsername())).thenReturn(User);
        User fromService = userServiceImpl.findByUsername(User.getUsername());
        Assert.assertEquals(User, fromService);
    }

    @Test
    public void updateUserIfGivenValidUser() {
        User User = buildMockUserDao();
        userServiceImpl.updateUser(User);
        Mockito.verify(userDao, Mockito.times(1)).updateUser(User);
    }

    @Test
    public void deleteUserByIdIfGivenValidId() {
        User User = buildMockUserDao();
        userServiceImpl.deleteUserById(User.getId());
        Mockito.verify(userDao, Mockito.times(1)).deleteUserById(User.getId());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void doNotDeleteUserByIdIfGivenInvalidId() {
        User User = buildMockUserDao();
        userServiceImpl.deleteUserById(null);
        Mockito.verify(userDao, Mockito.times(0)).deleteUserById(User.getId());
    }

    @Test
    public void saveUserAndFindAllUser_ReturnList_AssertThatTheListOfUserisNotEmpty() {
        User User = buildMockUserDao();
        userServiceImpl.saveUser(User);
        List<User> listOfUserBuiltFromService = userServiceImpl.findAllUsers();
        Assert.assertTrue(listOfUserBuiltFromService.isEmpty());
    }

    @Test
    public void saveMockUser_DeleteAllUser_VerifyDeletionOfAllUser() {
        User User = buildMockUserDao();
        userServiceImpl.saveUser(User);
        List<User> listOfUserBuiltBeforeDeletion = userServiceImpl.findAllUsers();
        userServiceImpl.deleteAllUsers();
        Assert.assertTrue(listOfUserBuiltBeforeDeletion.isEmpty());
    }

    @Test
    public void saveUserAndVerifyItsExistence() {
        User User = buildMockUserDao();
        userServiceImpl.saveUser(User);
        userServiceImpl.isUserExist(User);
        Mockito.verify(userDao, Mockito.times(1)).isUserExist(User);
    }

    public User buildMockUserDao() {
        return User.builder()
                .id((long) 1)
                .username("Ash Ketchum")
                .email("email@email.com")
                .address("address")
                .build();
    }
}
