package com.rolfie.netflixcatalogue.database.dao.serie;

import com.rolfie.netflixcatalogue.database.entity.SerieDO;
import org.jboss.weld.util.collections.ImmutableList;

import java.util.List;

public class MockedSerieDAO implements SerieDAO {

    public static SerieDAO create() {
        return new MockedSerieDAO();
    }

    public final List<SerieDO> fetchAll() {
        return ImmutableList.<SerieDO>builder()
                .add(SerieDO.newSerie(1, "La Case de Papel", true, 2017))
                .add(SerieDO.newSerie(2, "The 100", false, 2017))
                .add(SerieDO.newSerie(3, "Black Mirror", true, 2017))
                .build();
    }
}
