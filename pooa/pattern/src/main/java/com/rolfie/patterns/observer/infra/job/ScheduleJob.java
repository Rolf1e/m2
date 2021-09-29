package com.rolfie.patterns.observer.infra.job;

public interface ScheduleJob<R> {

    R execute();

}
