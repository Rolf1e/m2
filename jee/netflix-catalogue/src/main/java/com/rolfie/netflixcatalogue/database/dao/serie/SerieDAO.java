package com.rolfie.netflixcatalogue.database.dao.serie;

import com.rolfie.netflixcatalogue.database.entity.SerieDO;

import java.util.List;

public interface SerieDAO {

    List<SerieDO> fetchAll();
}
