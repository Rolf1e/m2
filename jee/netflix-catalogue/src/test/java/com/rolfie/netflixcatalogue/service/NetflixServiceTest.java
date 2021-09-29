package com.rolfie.netflixcatalogue.service;

import com.rolfie.netflixcatalogue.controller.dto.SerieDTO;
import com.rolfie.netflixcatalogue.database.dao.season.MockedSeasonDAO;
import com.rolfie.netflixcatalogue.database.dao.serie.MockedSerieDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NetflixServiceTest {

    private NetflixService netflixService;

    @BeforeEach
    public void init() {
        this.netflixService = NetflixService.create(MockedSeasonDAO.create(), MockedSerieDAO.create());
    }

    @Test
    public void should_fetch_series() {
        final List<SerieDTO> serieDTOS = netflixService.fetchSeries();

        Assertions.assertEquals(3, serieDTOS.size());
        final SerieDTO serieDTO = serieDTOS.get(0);

        Assertions.assertEquals("La casa de papel", serieDTO.getName());
        Assertions.assertEquals(2, serieDTO.getSeason());
        Assertions.assertEquals(22, serieDTO.getTotalEpisodes());
    }

}