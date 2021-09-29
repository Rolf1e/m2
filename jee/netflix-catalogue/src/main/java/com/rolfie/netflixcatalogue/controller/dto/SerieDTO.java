package com.rolfie.netflixcatalogue.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SerieDTO {

    private final String name;
    private final int season;
    private final int totalEpisodes;

    public static SerieDTO newSerie(final String name,
                                    final int season,
                                    final int totalEpisodes) {
        return new SerieDTO(name, season, totalEpisodes);
    }

}
