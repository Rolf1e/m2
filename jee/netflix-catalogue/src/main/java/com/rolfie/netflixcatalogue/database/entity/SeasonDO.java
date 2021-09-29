package com.rolfie.netflixcatalogue.database.entity;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SeasonDO {

    private final int fkSerie;
    private final int numSeaon;
    private final int nbEpisode;

    public static SeasonDO newSeason(final int fkSerie,
                                     final int numSeaon,
                                     final int nbEpisode) {

        return new SeasonDO(fkSerie, numSeaon, nbEpisode);
    }
}
