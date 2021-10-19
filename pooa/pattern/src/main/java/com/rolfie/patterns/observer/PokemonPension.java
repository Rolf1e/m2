package com.rolfie.patterns.observer;

import com.rolfie.patterns.observer.domain.dto.Pokemon;
import com.rolfie.patterns.observer.domain.dto.trainer.PokemonTrainer;
import com.rolfie.patterns.observer.infra.JavaScheduler;
import com.rolfie.patterns.observer.infra.Scheduler;
import com.rolfie.patterns.observer.infra.job.PokemonScheduleJob;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PokemonPension {

    private final Map<Long, List<Pokemon>> inPension;
    private final Scheduler scheduler;

    public static PokemonPension create() {
        return new PokemonPension(new HashMap<>(), JavaScheduler.create());
    }

    public void drop(final PokemonTrainer trainer,
                     final Pokemon pokemon) {

        final var uuid = trainer.getUuid();
        final var pokemons = Optional.ofNullable(inPension.get(uuid))
                .orElse(new ArrayList<>());

        inPension.put(uuid, addOneTo(pokemons, pokemon));
        suscribe(trainer);
    }

    private void suscribe(final PokemonTrainer trainer) {
        scheduler.register(PokemonScheduleJob.create(trainer.getDelayPokemonUpAtPension()));
    }

    private static <E> List<E> addOneTo(final List<E> elements,
                                        final E element) {
        final var list = new ArrayList<>(elements);
        list.add(element);
        return list;
    }
}
