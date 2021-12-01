package con.rolfie.dealabs.controller;

import con.rolfie.dealabs.config.DealConfig;
import con.rolfie.dealabs.config.UserConfig;
import con.rolfie.dealabs.model.dto.input.DealTemperatureDto;
import con.rolfie.dealabs.model.dto.input.NewDealDto;
import con.rolfie.dealabs.model.dto.input.TemperatureDirection;
import con.rolfie.dealabs.service.deal.DealService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {DealJpaTestConfig.class, DealConfig.class, UserConfig.class})
@Transactional
public class DealControllerTest {

    @Autowired
    private DealService dealService;
    private DealController dealController;

    @Before
    public void init() {
        this.dealController = new DealController(dealService);
    }

    @Test
    public void should_fetch_deals() {
        final var response = dealController.fetchAll();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        final var deals = Objects.requireNonNull(response.getBody());

        Assert.assertEquals(5, deals.size());
    }

    @Test
    public void should_get_details() {
        final var response = dealController.fetchDetails(1);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        final var details = Objects.requireNonNull(response.getBody());

        Assert.assertEquals(190.88, details.getPriceNew(), 0.001);
        Assert.assertEquals(265d, details.getPriceOld(), 0.001);
        Assert.assertEquals("PROMO_CODE", details.getPromoCode());

    }

    @Test
    public void should_not_find_details() {
        final var response = dealController.fetchDetails(10000);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void should_refresh_deal() {
        final var response = dealController.refresh(1);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        final var deal = Objects.requireNonNull(response.getBody());

        Assert.assertEquals("rolfie", deal.getCreator());
        Assert.assertEquals(LocalDateTime.of(2021, 10, 12, 13, 57, 54), deal.getDate());
        Assert.assertEquals("G.Skill Trident Z Neo F4-4000C18D-32GTZN Module de mémoire 32 Go 2 x 16 Go DDR4 4000 MHzTypeDDR4 SDRAMCouleurNoir/Argent/RGBEAN4713294224156Référence du fabrica", deal.getDescription());
        Assert.assertEquals("https://static-pepper.dealabs.com/threads/raw/default/2220841_1/re/234x330/qt/60/2220841_1.jpg", deal.getImgUrl());
        Assert.assertEquals("Mémoire RAM G.Skill Trident Z Neo (F4-4000C18D-32GTZN) - 32 Go (2x16 Go), DDR4, 4000 MHz", deal.getTitle());
        Assert.assertEquals("Amazon", deal.getShopName());
        Assert.assertEquals("https://www.dealabs.com/visit/thread/2220841?API=true&user_id={{user_id}}&device_id=7377839", deal.getShopLink());
        Assert.assertEquals(Integer.valueOf(0), deal.getTemperature());
    }

    @Test
    public void should_not_find_deal() {
        final var response = dealController.refresh(1000);

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void should_create_deal() {
        final var response = dealController.create(NewDealDto.create("aaa", "bbbbb", 2f, 1f, "shop", "CODE", 1l, "img"));

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        final var headers = response.getHeaders();

        Assert.assertEquals(URI.create("/deals/6"), headers.getLocation());
    }

    @Test
    public void should_not_create_deal_because_user_not_found() {
        final var response = dealController.create(NewDealDto.create("aaa", "bbbbb", 2f, 1f, "shop", "CODE", 10000l, "img"));

        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void should_update_temperature_only_once() {
        final var response = dealController.update(DealTemperatureDto.create(1L, "rolfie", TemperatureDirection.UP));

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        // We try update  UP again
        final var secondResponse = dealController.update(DealTemperatureDto.create(1L, "rolfie", TemperatureDirection.UP));

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, secondResponse.getStatusCode());

        // We try update DOWN
        final var thirdResponse = dealController.update(DealTemperatureDto.create(1L, "rolfie", TemperatureDirection.DOWN));
        Assert.assertEquals(HttpStatus.OK, thirdResponse.getStatusCode());
    }


}