package con.rolfie.dealabs.config;

import con.rolfie.dealabs.model.database.dao.DealRepository;
import con.rolfie.dealabs.service.deal.DealService;
import con.rolfie.dealabs.service.deal.DealServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DealConfig {

    @Bean
    public DealService dealService(final DealRepository dealRepository) {
        return DealServiceImpl.create(dealRepository);
    }

}
