package com.rolfie.netflixcatalogue.service;

import com.rolfie.netflixcatalogue.controller.dto.SerieDTO;
import com.rolfie.netflixcatalogue.database.dao.season.SeasonDAO;
import com.rolfie.netflixcatalogue.database.dao.serie.SerieDAO;
import com.rolfie.netflixcatalogue.database.entity.SerieDO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NetflixService {

    private final SeasonDAO seasonDAO;
    private final SerieDAO serieDAO;

    public static NetflixService create(final SeasonDAO seasonDAO,
                                        final SerieDAO serieDAO) {
        return new NetflixService(seasonDAO, serieDAO);
    }

    public List<SerieDTO> fetchSeries() {
        return serieDAO.fetchAll()
                .stream()
                .map(this::fetchDetailForSerie)
                .collect(Collectors.toList());
    }

    private SerieDTO fetchDetailForSerie(final SerieDO serie) {
        return SerieDTO.newSerie(
                serie.getName(),
                seasonDAO.countEpisode(serie.getId()),
                seasonDAO.countSeason(serie.getId())
        );
    }

}
