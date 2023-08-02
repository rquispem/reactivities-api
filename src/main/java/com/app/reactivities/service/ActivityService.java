package com.app.reactivities.service;

import com.app.reactivities.model.entity.Activity;

import java.util.List;
import java.util.UUID;

public interface ActivityService {
    List<Activity> retrieveAllActivities();
    Activity findActivityBy(UUID id);
    void createActivity(Activity activity);
    Activity editActivity(UUID id, Activity activity);
    void removeActivity(UUID id);
}
