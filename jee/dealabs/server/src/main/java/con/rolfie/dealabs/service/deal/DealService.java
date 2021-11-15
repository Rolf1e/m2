package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.dto.output.DealDetailsDto;
import con.rolfie.dealabs.model.dto.output.DealDto;
import con.rolfie.dealabs.model.dto.input.NewDealDto;

import java.util.List;
import java.util.Optional;

public interface DealService {

    List<DealDto> fetchOrderedDeals();

    Optional<DealDto> fetchDeal(final long id);

    Optional<DealDetailsDto> fetchDetails(final long id);

    /**
     * This method should checkout user informations and if it exists,
     * creates the {@link DealDo} and save in database
     */
    DealDto createAndSave(final NewDealDto newDeal) throws UserNotFoundException;
}
