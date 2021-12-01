package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.exception.TemperatureException;
import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.dto.input.DealTemperatureDto;
import con.rolfie.dealabs.model.dto.input.NewDealDto;
import con.rolfie.dealabs.model.dto.output.DealDetailsDto;
import con.rolfie.dealabs.model.dto.output.DealDto;

import java.util.List;
import java.util.Optional;

public interface DealService {

    List<DealDto> findOrdered();

    Optional<DealDto> fetch(final long id);

    Optional<DealDetailsDto> fetchDetails(final long id);

    Optional<Integer> fetchTemperature(final long id);

    /**
     * This method should checkout user informations and if it exists,
     * creates the {@link DealDo} and save in database
     */
    DealDto createAndSave(final NewDealDto newDeal) throws UserNotFoundException;

    void update(final DealTemperatureDto temperature) throws TemperatureException;
}
