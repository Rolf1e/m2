package com.rolfie.patterns.observer.domain.dto.trainer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.exception.ToMuchPokemonException;

public interface PensionActions {

    void acquire(final Pokemon pokemon) throws ToMuchPokemonException;

    void notify(final Pokemon pokemon);
}
