package com.rolfie.patterns.observer.infra;

import com.rolfie.patterns.observer.infra.job.JobResult;
import com.rolfie.patterns.observer.infra.job.ScheduleJob;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class JavaScheduler<R> implements Scheduler<R> {

    private final List<ScheduleJob<R>> jobsHolder;

    public static <R> Scheduler<R> create() {
        return new JavaScheduler<>(new ArrayList<>());
    }

    @Override
    public List<JobResult<R>> getResults() {
        return null;
    }

    @Override
    public void register(final ScheduleJob<R> job) {
        jobsHolder.add(job);
    }
}
