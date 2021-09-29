package com.rolfie.patterns.observer.infra;

import com.rolfie.patterns.observer.infra.job.ScheduleJob;

public interface Scheduler<R> {

    R register(final ScheduleJob<R> job);
}
