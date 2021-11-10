package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.model.dto.DealDto;
import con.rolfie.dealabs.model.dto.DealDetailsDto;
import con.rolfie.dealabs.model.dto.NewDealDto;
import con.rolfie.dealabs.service.deal.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping(value = "/all")
    public final ResponseEntity<List<DealDto>> fetchDeals() {
        return ResponseEntity.ok(dealService.fetchOrderedDeals());
    }

    @GetMapping(value = "/details/{id}")
    public final ResponseEntity<DealDetailsDto> fetchDetails(@PathVariable("id") final long id) {
        final var dealDetails = dealService.fetchDetails(id);
        if (dealDetails.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dealDetails.get());
    }

    @GetMapping(value = "/{id}")
    public final ResponseEntity<DealDto> refresh(@PathVariable("id") final long id) {
        final var deal = dealService.fetchDeal(id);
        if (deal.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(deal.get());
    }

    @PostMapping(value = "")
    public final ResponseEntity<DealDto> create(@RequestBody final NewDealDto deal) {
        return ResponseEntity.ok(dealService.createAndSave(deal));
    }

}
