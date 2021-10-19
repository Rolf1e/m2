package com.rolfie.patterns.observer.infra.job;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JobResult<R> {

    private final R result;

    public static <R> JobResult<R> create(final R result) {
        return new JobResult<>(result);
    }
}
