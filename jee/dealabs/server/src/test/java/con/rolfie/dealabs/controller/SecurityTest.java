package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.config.BasicSecurityConfig;
import con.rolfie.dealabs.config.DealConfig;
import con.rolfie.dealabs.config.UserConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {DealJpaTestConfig.class, DealConfig.class, UserConfig.class, BasicSecurityConfig.class})
@EnableAutoConfiguration
public class SecurityTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_be_401() {
        Arrays.asList("/users/1")
                .forEach(route -> checkStatusOnRoute(route, HttpStatus.UNAUTHORIZED, "bad", "bad"));
    }

    @Test
    public void should_be_200() {
        Arrays.asList("/users/1")
                .forEach(route -> checkStatusOnRoute(route, HttpStatus.OK, "tigran", "a"));
    }


    private void checkStatusOnRoute(final String route,
                                    final HttpStatus status,
                                    final String username,
                                    final String password) {

        final var response = restTemplate.withBasicAuth(username, password)
                .getForEntity(route, Object.class);

        Assert.assertEquals(status, response.getStatusCode());

    }


}
