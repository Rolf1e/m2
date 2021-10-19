package com.rolfie.patterns.observer.infra;

import com.rolfie.patterns.observer.infra.job.JobResult;
import com.rolfie.patterns.observer.infra.job.ScheduleJob;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaScheduler<R> implements Scheduler<R> {

    private final ScheduledExecutorService scheduler;
    private final Map<String, JobResult<R>> results;

    public static <R> Scheduler<R> create() {
        return new JavaScheduler<>(Executors.newScheduledThreadPool(1), new HashMap<>());
    }

    @Override
    public List<JobResult<R>> getResults() {
        return null;
    }

    @Override
    public void register(final ScheduleJob<R> job) throws ExecutionException, InterruptedException {
        final ScheduledFuture<JobResult<R>> schedule = scheduler.schedule(job::execute, job.getDelay(), job.getTimeUnit());
        results.put("", schedule.get());
    }
}
