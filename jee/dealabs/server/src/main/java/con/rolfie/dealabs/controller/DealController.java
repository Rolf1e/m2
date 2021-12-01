package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.exception.TemperatureException;
import con.rolfie.dealabs.exception.UserNotFoundException;
import con.rolfie.dealabs.model.dto.input.DealTemperatureDto;
import con.rolfie.dealabs.model.dto.input.NewDealDto;
import con.rolfie.dealabs.model.dto.output.DealDetailsDto;
import con.rolfie.dealabs.model.dto.output.DealDto;
import con.rolfie.dealabs.service.deal.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping(value = "/public/deals/all", produces = "application/json")
    public final ResponseEntity<List<DealDto>> fetchAll() {
        return ResponseEntity.ok(dealService.findOrdered());
    }

    @GetMapping(value = "/public/deals/details/{id}")
    public final ResponseEntity<DealDetailsDto> fetchDetails(@PathVariable("id") final long id) {
        return dealService.fetchDetails(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = "/public/deals/details/temperature/{id}")
    public final ResponseEntity<Integer> fetchTemperature(@PathVariable("id") final long id) {
        return dealService.fetchTemperature(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/public/deals/{id}")
    public final ResponseEntity<DealDto> refresh(@PathVariable("id") final long id) {
        return dealService.fetch(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/deals/temperature")
    public final ResponseEntity<String> update(@RequestBody final DealTemperatureDto temperature) {
        try {
            dealService.update(temperature);
            return ResponseEntity.ok().build();
        } catch (TemperatureException e) {
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    @PostMapping(value = "/deals")
    public final ResponseEntity<String> create(@RequestBody final NewDealDto deal) {
        try {
            final var createdDeal = dealService.createAndSave(deal);
            return ResponseEntity.created(URI.create("/deals/" + createdDeal.getId()))
                    .build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
