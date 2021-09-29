package com.rolfie.patterns.observer.domain.dto.trainer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.exception.ToMuchPokemonException;

public class Trainee extends BaseTrainer {

    private static final int MAX_TEAM_SIZE = 2;

    public static PokemonTrainer create() {
        return new Trainee();
    }

    private Trainee() {
        super(MAX_TEAM_SIZE);
    }

    @Override
    public final void acquire(final Pokemon pokemon) throws ToMuchPokemonException {
        final var size = getPokemons().size();
        if (MAX_TEAM_SIZE <= size) {
            throw new ToMuchPokemonException(MAX_TEAM_SIZE);
        }
        addPokemon(pokemon);
    }

    @Override
    public final void notify(final Pokemon pokemon) {

    }
}
