package com.rolfie.patterns.observer.domain.exception;

public class LevelMaxException extends Exception {
    public LevelMaxException() {
        super("Already level 100 !");
    }
}
