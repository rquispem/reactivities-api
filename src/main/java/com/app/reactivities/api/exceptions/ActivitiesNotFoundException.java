package com.app.reactivities.api.exceptions;

public class ActivitiesNotFoundException extends RuntimeException {
    public ActivitiesNotFoundException(String ActivityId) {
        super("Activity with id: "+ ActivityId+" not found");
    }
}
