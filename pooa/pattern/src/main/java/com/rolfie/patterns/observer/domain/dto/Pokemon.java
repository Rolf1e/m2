package com.rolfie.patterns.observer.domain.dto;

import com.rolfie.patterns.observer.domain.exception.LevelMaxException;
import lombok.Getter;

@Getter
public class Pokemon {

    private final String name;
    private final String nickname;
    private final PokemonType type;
    private int level;

    public static Pokemon create(final String name,
                                 final PokemonType type) {
        return new Pokemon(name, name, type);
    }

    private Pokemon(final String name,
                    final String nickname,
                    final PokemonType type) {
        this.name = name;
        this.nickname = nickname;
        this.type = type;
        this.level = 0;
    }

    public void levelUp() throws LevelMaxException {
        if (level + 1 < 101) {
            level++;
            return;
        }
        throw new LevelMaxException();
    }
}
