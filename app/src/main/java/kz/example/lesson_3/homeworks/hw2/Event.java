package kz.example.lesson_3.homeworks.hw2;

import java.io.Serializable;

public class Event implements Serializable {

    private long calendarId;
    private String title;
    private String description;
    private long dtStart;
    private long dtEnd;
    private boolean allDay;

    public Event(long calendarId, String title, String description, long dtStart, long dtEnd, boolean allDay) {
        this.calendarId = calendarId;
        this.title = title;
        this.description = description;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.allDay = allDay;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getStartTime() {
        return dtStart;
    }

    public long getEndTime() {
        return dtEnd;
    }

    public boolean isAllDay() {
        return allDay;
    }

}
