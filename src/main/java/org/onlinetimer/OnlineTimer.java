package org.onlinetimer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.onlinetimer.model.WorkTimeUnit;


@ManagedBean
@ApplicationScoped
public class OnlineTimer {

    private static final String EDIT_BUTTON_EDIT = "Edit";
    private static final String EDIT_BUTTON_SAVE = "Save";

    private WorkTimeUnit timeUnit;
    private List<WorkTimeUnit> list;
    private LocalTime totalDuration;
    private boolean status;
    private String editButtonValue;
    private boolean editButton;

    public String getStartTime() {
        return (timeUnit.getStartTime() != null)
                ? timeUnit.getStartTime().format(DateTimeFormatter.ISO_TIME)
                : "";
    }

    public String getStopTime() {
        return (timeUnit.getStopTime() != null)
                ? timeUnit.getStopTime().format(DateTimeFormatter.ISO_TIME)
                : "";
    }

    public String getDuration() {
        if (timeUnit.getDuration() != null) {
            return timeUnit.getDuration().format(DateTimeFormatter.ISO_TIME);
        }
        return "";
    }

    public List<WorkTimeUnit> getList() {
        return list;
    }

    public String getTotalDuration() {
        return totalDuration.format(DateTimeFormatter.ISO_TIME);
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getEditButtonValue() {
        return editButtonValue;
    }

    public boolean isEditButton() {
        return editButton;
    }

    public void actionListenerSetStartTime() {
        timeUnit = new WorkTimeUnit(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        this.status = true;
    }

    public void actionListenerSetStopTime() {
        if (timeUnit.getStopTime() == null) {
            timeUnit.setStopTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
            this.list.add(timeUnit);
            this.status = false;
            this.totalDuration = getTotalDuration(this.totalDuration, timeUnit.getDuration());
        }
    }

    public void actionListenerReset() {
        this.timeUnit = new WorkTimeUnit();
        this.totalDuration = LocalTime.of(0, 0, 0);
        this.list.clear();
        this.status = false;
    }

    public void actionListenerEditDescription() {
        if (this.editButton) {
            this.editButtonValue = EDIT_BUTTON_SAVE;
            this.editButton = false;
            System.err.println("edit: " + editButton);
        } else {
            this.editButtonValue = EDIT_BUTTON_EDIT;
            this.editButton = true;
            System.err.println("edit: " + editButton);
        }
    }

    @PostConstruct
    protected void init() {
        timeUnit = new WorkTimeUnit();
        list = new ArrayList<>();
        totalDuration = LocalTime.of(0, 0, 0, 0);
        status = false;
        editButton = true;
        editButtonValue = EDIT_BUTTON_EDIT;
    }

    private LocalTime getTotalDuration(LocalTime t1, LocalDateTime t2) {
        return t1.plusHours(t2.getHour())
                .plusMinutes(t2.getMinute())
                .plusSeconds(t2.getSecond())
                .plusNanos(t2.getNano());
    }
}