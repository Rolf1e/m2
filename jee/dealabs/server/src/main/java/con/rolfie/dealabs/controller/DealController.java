package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.model.dto.DealDTO;
import con.rolfie.dealabs.model.dto.DealDetailsDTO;
import con.rolfie.dealabs.service.deal.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @GetMapping(value = "/all")
    public final ResponseEntity<List<DealDTO>> fetchDeals() {
        return ResponseEntity.ok(dealService.fetchOrderedDeals());
    }

    @GetMapping(value = "/{id}")
    public final ResponseEntity<DealDetailsDTO> fetchDetails(@PathVariable("id") final long id) {
        final var dealDetailsDTO = dealService.fetchDetails(id);
        if (dealDetailsDTO.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dealDetailsDTO.get());
    }

}
