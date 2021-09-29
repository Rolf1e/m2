package com.rolfie.patterns.observer.infra.job;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PokemonScheduleJob implements ScheduleJob<Pokemon> {

    public static ScheduleJob<Pokemon> create() {
        return new PokemonScheduleJob();
    }

    @Override
    public Pokemon execute() {
        return null;
    }
}
