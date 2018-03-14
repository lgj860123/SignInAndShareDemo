package com.pybeta.daymatter.signinandsharedemo.bean;

/**
 * eventBus对象
 * Created by luogj on 2018/2/10.
 */

public class EventBean {
    private String eventDesc;

    public EventBean(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }
}
