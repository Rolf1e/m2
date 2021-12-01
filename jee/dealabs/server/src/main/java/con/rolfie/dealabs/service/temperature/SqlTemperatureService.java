package con.rolfie.dealabs.service.temperature;

import con.rolfie.dealabs.exception.TemperatureUpdateException;
import con.rolfie.dealabs.model.database.dao.TemperatureRepository;
import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.database.entity.TemperatureDo;
import con.rolfie.dealabs.model.database.entity.UserDo;
import con.rolfie.dealabs.model.dto.input.TemperatureDirection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlTemperatureService implements TemperatureService {

    private final TemperatureRepository temperatureRepository;

    public static TemperatureService create(final TemperatureRepository temperatureRepository) {
        return new SqlTemperatureService(temperatureRepository);
    }

    @Override
    public void update(final DealDo deal, final UserDo user, final TemperatureDirection direction) throws TemperatureUpdateException {
        final var temperature = temperatureRepository.findByDealAndUser(deal, user);

        if (temperature.isPresent()) {
            updateIfPresent(direction, temperature.get());
            return;
        }


        final var newTemperature = new TemperatureDo();
        newTemperature.setDeal(deal);
        newTemperature.setUser(user);
        newTemperature.setValue(direction.getValue());
        temperatureRepository.save(newTemperature);

    }

    private void updateIfPresent(final TemperatureDirection direction,
                                 final TemperatureDo temperature) throws TemperatureUpdateException {

        if (isSameDirection(direction, temperature)) {
            throw new TemperatureUpdateException("Temperature can not be " + direction);
        }

        temperature.setValue(direction.getValue());
        temperatureRepository.save(temperature);
    }

    private boolean isSameDirection(final TemperatureDirection direction,
                                    final TemperatureDo temperature) {
        return direction.getValue().equals(temperature.getValue());
    }
}
