package com.epam.gameservice.business.event.events;

import org.springframework.context.ApplicationEvent;

public class GameSaveEvent extends ApplicationEvent {

    public GameSaveEvent(String gameName) {
        super(gameName);
    }
}