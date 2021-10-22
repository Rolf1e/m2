package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.model.dto.DealDTO;

import java.util.List;

public interface DealService {

    List<DealDTO> fetchOrderedDeals();
}
