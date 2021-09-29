package com.rolfie.patterns.observer.domain.exception;

public class ToMuchPokemonException extends Exception {
    public ToMuchPokemonException() {
        super("Pokemon teams are limited to 6 pokemons");
    }

    public ToMuchPokemonException(final int max) {
        super("Your team is limited to " + max + " pokemons");
    }
}
