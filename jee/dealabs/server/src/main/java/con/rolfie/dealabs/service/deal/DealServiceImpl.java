package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.model.database.dao.DealRepository;
import con.rolfie.dealabs.model.dto.DealDTO;
import con.rolfie.dealabs.model.dto.DealDetailsDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    public static DealService create(final DealRepository dealRepository) {
        return new DealServiceImpl(dealRepository);
    }

    @Override
    public List<DealDTO> fetchOrderedDeals() {
        return dealRepository.findAll()
                .stream()
                .map(DealDTO::from)
                .sorted(Comparator.comparing(DealDTO::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DealDTO> fetchDeal(final long id) {
        return dealRepository.findById(id)
                .map(DealDTO::from);
    }

    @Override
    public Optional<DealDetailsDTO> fetchDetails(final long id) {
        return dealRepository.findById(id)
                .map(DealDetailsDTO::from);
    }

}
