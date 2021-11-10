package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.model.dto.DealDTO;
import con.rolfie.dealabs.model.dto.DealDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface DealService {

    List<DealDTO> fetchOrderedDeals();

    Optional<DealDTO> fetchDeal(final long id);

    Optional<DealDetailsDTO> fetchDetails(final long id);
}
