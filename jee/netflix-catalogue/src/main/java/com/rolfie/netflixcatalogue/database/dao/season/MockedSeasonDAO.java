package com.rolfie.netflixcatalogue.database.dao.season;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MockedSeasonDAO implements SeasonDAO {

    public static SeasonDAO create() {
        return new MockedSeasonDAO();
    }

    public int countEpisode(final int fkSerie) {
        switch (fkSerie) {
            case 1:
                return 22;
            case 2:
                return 58;
            case 3:
                return 19;
            default:
                throw new IllegalStateException("Serie does not exist !");
        }
    }

    public int countSeason(final int fkSerie) {
        switch (fkSerie) {
            case 1:
                return 2;
            case 2:
            case 3:
                return 4;
            default:
                throw new IllegalStateException("Serie does not exist !");
        }
    }

}
