package con.rolfie.dealabs.config;

import con.rolfie.dealabs.model.database.dao.DealRepository;
import con.rolfie.dealabs.service.deal.DealService;
import con.rolfie.dealabs.service.deal.DealServiceImpl;
import con.rolfie.dealabs.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DealConfig {

    @Bean
    public DealService dealService(final DealRepository dealRepository,
                                   final UserService userService) {
        return DealServiceImpl.create(dealRepository, userService);
    }


}
