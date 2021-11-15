package con.rolfie.dealabs.service.deal;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.database.dao.DealRepository;
import con.rolfie.dealabs.model.database.entity.DealDo;
import con.rolfie.dealabs.model.database.entity.UserDo;
import con.rolfie.dealabs.model.dto.output.DealDetailsDto;
import con.rolfie.dealabs.model.dto.output.DealDto;
import con.rolfie.dealabs.model.dto.input.NewDealDto;
import con.rolfie.dealabs.model.dto.output.UserDto;
import con.rolfie.dealabs.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SqlDealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final UserService userService;

    public static DealService create(final DealRepository dealRepository,
                                     final UserService userService) {
        return new SqlDealServiceImpl(dealRepository, userService);
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
    public DealDto createAndSave(final NewDealDto newDeal) throws UserNotFoundException {
        final var byId = userService.findById(newDeal.getCreatorId());
        return DealDto.from(dealRepository.save(createFromInput(newDeal, byId)));
    }

    private DealDo createFromInput(final NewDealDto newDeal,
                                   final UserDto user) {
        final var toBeSaved = new DealDo();
        toBeSaved.setTitle(newDeal.getTitle());
        toBeSaved.setDescription(newDeal.getDescription());
        toBeSaved.setCreator(user.getUserDo());
        toBeSaved.setDate(LocalDateTime.now());
        toBeSaved.setPriceNew(newDeal.getPriceNew());
        toBeSaved.setPriceOld(newDeal.getPriceOld());
        toBeSaved.setPromoCode(newDeal.getPromoCode());
        toBeSaved.setShopName(newDeal.getShopName());
        toBeSaved.setImgUrl(newDeal.getImgUrl());
        toBeSaved.setTemperatures(Collections.emptyList());

        return toBeSaved;
    }


}
