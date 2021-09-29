package com.rolfie.patterns.observer.domain.dto.trainer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.exception.ToMuchPokemonException;

import java.util.List;

public interface PokemonTrainer {

    long getUuid();

    List<Pokemon> getPokemons();

    void acquire(final Pokemon pokemon) throws ToMuchPokemonException;

    void notify(final Pokemon pokemon);
}
