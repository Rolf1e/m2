package con.rolfie.dealabs.config;

import con.rolfie.dealabs.model.database.dao.DealRepository;
import con.rolfie.dealabs.model.database.dao.TemperatureRepository;
import con.rolfie.dealabs.service.deal.DealService;
import con.rolfie.dealabs.service.deal.SqlDealServiceImpl;
import con.rolfie.dealabs.service.temperature.SqlTemperatureService;
import con.rolfie.dealabs.service.temperature.TemperatureService;
import con.rolfie.dealabs.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DealConfig {

    @Bean
    public DealService dealService(final DealRepository dealRepository,
                                   final UserService userService,
                                   final TemperatureService temperatureService) {
        return SqlDealServiceImpl.create(dealRepository, userService, temperatureService);
    }

    @Bean
    public TemperatureService temperatureService(final TemperatureRepository temperatureRepository) {
        return SqlTemperatureService.create(temperatureRepository);
    }


}
