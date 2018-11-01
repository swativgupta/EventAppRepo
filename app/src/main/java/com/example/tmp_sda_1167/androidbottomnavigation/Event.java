package com.example.tmp_sda_1167.androidbottomnavigation;

public class Event {

    private String eventTitle;
    private String eventType;
    private String eventLocation;

    public Event(String eventTitle,String eventType,String eventLocation) {
        this.eventTitle = eventTitle;
        this.eventType=eventType;
        this.eventLocation=eventLocation;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
