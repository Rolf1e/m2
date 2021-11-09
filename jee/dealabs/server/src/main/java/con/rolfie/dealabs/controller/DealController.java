package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.model.dto.DealDTO;
import con.rolfie.dealabs.service.deal.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping(value = "/all")
    public final List<DealDTO> fetchDeals() {
        return dealService.fetchOrderedDeals();
    }

}
