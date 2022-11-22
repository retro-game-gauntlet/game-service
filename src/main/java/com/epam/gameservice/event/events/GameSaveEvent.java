package com.epam.gameservice.event.events;

import org.springframework.context.ApplicationEvent;

public class GameSaveEvent extends ApplicationEvent {

    public GameSaveEvent(String gameName) {
        super(gameName);
    }
}