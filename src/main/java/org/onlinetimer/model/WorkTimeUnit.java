package org.onlinetimer.model;

import java.time.LocalDateTime;

public class WorkTimeUnit {

    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private LocalDateTime duration;
    private String description;

    public WorkTimeUnit() {
    }

    public WorkTimeUnit(LocalDateTime start) {
        this.startTime = start;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
        this.setDuration();
    }

    public LocalDateTime getDuration() {
        return duration;
    }
    
    private void setDuration() {
        if (this.stopTime != null) {
            this.duration = this.stopTime
                    .minusHours(startTime.getHour())
                    .minusMinutes(startTime.getMinute())
                    .minusSeconds(startTime.getSecond())
                    .minusNanos(startTime.getNano());
        }
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String desc){
        this.description = desc;
    }

}