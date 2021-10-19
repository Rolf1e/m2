package com.rolfie.patterns.observer.infra.job;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public interface ScheduleJob<R> {

    JobResult<R> execute() throws ExecutionException;

    long getDelay();

    default TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

}
