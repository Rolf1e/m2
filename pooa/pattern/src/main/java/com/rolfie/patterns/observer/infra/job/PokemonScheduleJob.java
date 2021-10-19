package com.rolfie.patterns.observer.infra.job;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.exception.LevelMaxException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PokemonScheduleJob implements ScheduleJob<Pokemon> {

    @Getter
    private final long delay;
    private final Pokemon customerPokemon;


    public static ScheduleJob<Pokemon> create(final long delay,
                                              final Pokemon customerPokemon) {
        return new PokemonScheduleJob(delay, customerPokemon);
    }

    @Override
    public JobResult<Pokemon> execute() throws ExecutionException {
        try {
            customerPokemon.levelUp();
        } catch (LevelMaxException e) {
            throw new ExecutionException(customerPokemon.getNickname() + " is " + e.getMessage(), e);
        }
        return JobResult.create(customerPokemon);
    }

}
