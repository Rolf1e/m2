package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.dto.DealDto;
import con.rolfie.dealabs.model.dto.DealDetailsDto;
import con.rolfie.dealabs.model.dto.NewDealDto;
import con.rolfie.dealabs.service.deal.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        return dealService.fetchDetails(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(value = "/{id}")
    public final ResponseEntity<DealDto> refresh(@PathVariable("id") final long id) {
        return dealService.fetchDeal(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "")
    public final ResponseEntity<DealDto> create(@RequestBody final NewDealDto deal) {
        try {
            final DealDto createdDeal = dealService.createAndSave(deal);
            return ResponseEntity.created(URI.create("http://localhost:8080/deals/" + createdDeal.getId()))
                    .build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
