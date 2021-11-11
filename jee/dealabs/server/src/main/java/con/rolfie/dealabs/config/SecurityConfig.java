package con.rolfie.dealabs.config;

import con.rolfie.dealabs.model.database.dao.UserRepository;
import con.rolfie.dealabs.service.user.UserService;
import con.rolfie.dealabs.service.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public UserService userService(final UserRepository userRepository) {
        return UserServiceImpl.create(userRepository);
    }
}
