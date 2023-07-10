package com.app.reactivities.api;

import com.app.reactivities.model.entity.Activity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    EntityManager em;

    @GetMapping
    public List<Activity> retrieveAll() {
        TypedQuery<Activity> activityQuery = em.createQuery("SELECT a FROM Activity a", Activity.class);
        return activityQuery.getResultList();
    }
}
