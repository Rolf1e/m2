package com.rolfie.patterns.observer.domain.dto.trainer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;

import java.util.List;

public interface PokemonTrainer extends DelayAtPension, PensionActions {

    long getUuid();

    List<Pokemon> getPokemons();

}
