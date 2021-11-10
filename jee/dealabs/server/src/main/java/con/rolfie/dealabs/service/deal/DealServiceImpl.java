package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.model.database.dao.DealRepository;
import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.dto.DealDetailsDto;
import con.rolfie.dealabs.model.dto.DealDto;
import con.rolfie.dealabs.model.dto.NewDealDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
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
    public List<DealDto> fetchOrderedDeals() {
        return dealRepository.findAll()
                .stream()
                .map(DealDto::from)
                .sorted(Comparator.comparing(DealDto::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DealDto> fetchDeal(final long id) {
        return dealRepository.findById(id)
                .map(DealDto::from);
    }

    @Override
    public Optional<DealDetailsDto> fetchDetails(final long id) {
        return dealRepository.findById(id)
                .map(DealDetailsDto::from);
    }

    @Override
    public DealDto createAndSave(final NewDealDto newDeal) {
        return DealDto.from(dealRepository.save(createFromInput(newDeal)));
    }

    private DealDo createFromInput(final NewDealDto newDeal) {
        final DealDo toBeSaved = new DealDo();
        toBeSaved.setTitle(newDeal.getTitle());
        toBeSaved.setDescription(newDeal.getDescription());
        toBeSaved.setCreator(newDeal.getCreator());
        toBeSaved.setDate(LocalDate.now());
        toBeSaved.setPriceNew(newDeal.getPriceNew());
        toBeSaved.setPriceOld(newDeal.getPriceOld());
        toBeSaved.setPromoCode(newDeal.getPromoCode());
        toBeSaved.setShopName(newDeal.getShopName());
        toBeSaved.setImgUrl(newDeal.getImgUrl());
        return toBeSaved;
    }

}
