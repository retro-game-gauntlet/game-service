package com.epam.gameservice.event.events;

import org.springframework.context.ApplicationEvent;

public class PlatformSaveEvent extends ApplicationEvent {

    public PlatformSaveEvent(String code) {
        super(code);
    }
}