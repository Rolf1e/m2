package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.model.dto.DealDetailsDto;
import con.rolfie.dealabs.model.dto.DealDto;
import con.rolfie.dealabs.model.dto.NewDealDto;

import java.util.List;
import java.util.Optional;

public interface DealService {

    List<DealDto> fetchOrderedDeals();

    Optional<DealDto> fetchDeal(final long id);

    Optional<DealDetailsDto> fetchDetails(final long id);

    DealDto createAndSave(final NewDealDto newDeal);
}
