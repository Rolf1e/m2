package com.rolfie.patterns.observer.infra;

import com.rolfie.patterns.observer.infra.job.ScheduleJob;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaScheduler<R> implements Scheduler<R> {

    public static <R> Scheduler<R> create() {
        return new JavaScheduler<>();
    }

    @Override
    public R register(final ScheduleJob<R> job) {
        return job.execute();
    }
}
