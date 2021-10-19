package com.rolfie.patterns.observer.infra;

import com.rolfie.patterns.observer.infra.job.JobResult;
import com.rolfie.patterns.observer.infra.job.ScheduleJob;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface Scheduler<R> {

    List<JobResult<R>> getResults();

    void register(final ScheduleJob<R> job) throws ExecutionException, InterruptedException;
}
