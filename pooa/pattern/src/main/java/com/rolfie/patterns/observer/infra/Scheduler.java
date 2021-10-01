package com.rolfie.patterns.observer.infra;

import com.rolfie.patterns.observer.infra.job.JobResult;
import com.rolfie.patterns.observer.infra.job.ScheduleJob;

import java.util.List;

public interface Scheduler<R> {

    List<JobResult<R>> getResults();

    void register(final ScheduleJob<R> job);
}
