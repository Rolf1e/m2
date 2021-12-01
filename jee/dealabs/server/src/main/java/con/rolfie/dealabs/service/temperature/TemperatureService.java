package con.rolfie.dealabs.service.temperature;

import con.rolfie.dealabs.exception.TemperatureException;
import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.database.entity.UserDo;
import con.rolfie.dealabs.model.dto.input.TemperatureDirection;

public interface TemperatureService {

    void update(final DealDo deal, final UserDo user, final TemperatureDirection direction) throws TemperatureException;

}
