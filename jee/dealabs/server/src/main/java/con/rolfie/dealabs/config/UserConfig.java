package con.rolfie.dealabs.config;

import con.rolfie.dealabs.model.database.dao.UserRepository;
import con.rolfie.dealabs.security.provider.UserAuthenticationProvider;
import con.rolfie.dealabs.service.user.UserService;
import con.rolfie.dealabs.service.user.SqlUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(final UserRepository userRepository) {
        return SqlUserService.create(userRepository);
    }

    @Bean
    public static UserAuthenticationProvider userAuthenticationProvider(final UserService userService) {
        return UserAuthenticationProvider.create(userService);
    }
}
