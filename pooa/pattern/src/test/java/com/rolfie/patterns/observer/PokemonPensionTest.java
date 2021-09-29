package com.rolfie.patterns.observer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.dto.PokemonType;
import com.rolfie.patterns.observer.domain.dto.trainer.Trainee;
import com.rolfie.patterns.observer.domain.exception.ToMuchPokemonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonPensionTest {

    private PokemonPension pokemonPension;

    @BeforeEach
    public void init() {
        pokemonPension = PokemonPension.create();
    }

    @Test
    public void should_create_trainer_and_add_pokemon() throws ToMuchPokemonException {
        final var trainer = Trainee.create();
        trainer.acquire(Pokemon.create("Pikachu", PokemonType.ELECTRIC));
    }

    @Test()
    public void should_create_trainer_and_add_pokemon_two_pokemons() {
        Assertions.assertThrows(ToMuchPokemonException.class, () -> {
            final var trainer = Trainee.create();
            trainer.acquire(Pokemon.create("Pikachu", PokemonType.ELECTRIC));
            trainer.acquire(Pokemon.create("Pikachu", PokemonType.ELECTRIC));
            trainer.acquire(Pokemon.create("Pikachu", PokemonType.ELECTRIC));
        });
    }

    @Test
    public void should_notify_when_egg_is_scratched() {
        final var trainee = Trainee.create();
        pokemonPension.drop(trainee, Pokemon.create("Pikachu", PokemonType.ELECTRIC));


    }

}