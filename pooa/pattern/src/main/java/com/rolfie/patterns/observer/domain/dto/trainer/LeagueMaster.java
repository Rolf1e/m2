package com.rolfie.patterns.observer.domain.dto.trainer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.exception.ToMuchPokemonException;

public class LeagueMaster extends BaseTrainer {

    public static PokemonTrainer create() {
        return new LeagueMaster();
    }

    private LeagueMaster() {
        super();
    }

    @Override
    public final void acquire(Pokemon pokemon) throws ToMuchPokemonException {
        addPokemon(pokemon);
    }
}
