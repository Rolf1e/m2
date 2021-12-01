package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.config.UserConfig;
import con.rolfie.dealabs.model.dto.input.NewUserDto;
import con.rolfie.dealabs.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Objects;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {DealJpaTestConfig.class, UserConfig.class})
@Transactional
public class UserControllerTest {

    @Autowired
    private UserService userService;
    private UserController userController;

    @Before
    public void init() {
        this.userController = new UserController(userService);
    }

    @Test
    public void should_create_user() {
        final var response = userController.create(NewUserDto.create("a", "aa", "aaa", "aaaa"));

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        final var headers = response.getHeaders();

        Assert.assertEquals(URI.create("http://localhost:8080/users/5"), headers.getLocation());
    }

    @Test
    public void should_fetch_user() {
        final var response = userController.fetchById(1);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        final var user = Objects.requireNonNull(response.getBody());

        Assert.assertEquals("rolfie", user.getNickname());
        Assert.assertEquals("tigran", user.getFirstName());
        Assert.assertEquals("tigran", user.getLastName());
    }

    @Test
    public void should_not_fetch_user() {
        final var response = userController.fetchById(100000);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}