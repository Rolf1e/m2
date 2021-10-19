package com.rolfie.patterns.observer.domain.dto.trainer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.exception.ToMuchPokemonException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class BaseTrainer implements PokemonTrainer, DelayAtPension {

    private static final int MAX_TEAM_SIZE = 6;
    private static final long ONE_MINUTE_DELAY_IN_SECONDS = 60;

    private static long UUID_COUNT = 0;

    private final long uuid;
    private final Pokemon[] pokemons;

    protected BaseTrainer() {
        this.uuid = UUID_COUNT;
        UUID_COUNT++;
        this.pokemons = new Pokemon[MAX_TEAM_SIZE];
    }

    protected BaseTrainer(final int maxTeamSize) {
        this.uuid = UUID_COUNT;
        UUID_COUNT++;
        this.pokemons = new Pokemon[maxTeamSize];
    }

    protected final void addPokemon(final Pokemon pokemon) throws ToMuchPokemonException {
        final var pokemons = getPokemons();
        final int size = pokemons.size();
        if (MAX_TEAM_SIZE <= size) {
            throw new ToMuchPokemonException();
        }
        this.pokemons[size] = pokemon;
    }

    @Override
    public long getDelayPokemonUpAtPension() {
        return ONE_MINUTE_DELAY_IN_SECONDS;
    }

    @Override
    public final long getUuid() {
        return uuid;
    }

    @Override
    public final List<Pokemon> getPokemons() {
        return Arrays.stream(pokemons)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
