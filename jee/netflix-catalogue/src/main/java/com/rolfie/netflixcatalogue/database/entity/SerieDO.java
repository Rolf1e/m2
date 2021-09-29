package com.rolfie.netflixcatalogue.database.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SerieDO {

    @Getter
    private final int id;
    @Getter
    private final String name;
    private final boolean originalProduction;
    private final int year;

    public static SerieDO newSerie(final int id,
                                   final String name,
                                   final boolean originalProduction,
                                   final int year) {
        return new SerieDO(id, name, originalProduction, year);
    }

}
