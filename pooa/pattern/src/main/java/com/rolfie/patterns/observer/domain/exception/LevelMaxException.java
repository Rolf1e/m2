package com.rolfie.patterns.observer.domain.exception;

public class LevelMaxException extends Exception {
    public LevelMaxException() {
        super("already level 100 !");
    }
}
