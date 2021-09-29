package com.rolfie.netflixcatalogue.database.dao.season;

public interface SeasonDAO {

    int countEpisode(final int fkSerie);

    int countSeason(final int fkSerie);

}
