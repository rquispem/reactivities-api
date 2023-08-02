package com.app.reactivities.api;

import com.app.reactivities.model.entity.Activity;
import com.app.reactivities.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
//@RequestMapping(value = "/activities", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/activities")
@RequiredArgsConstructor
//@Validated
public class ActivityController {

    private final ActivityService activityService;
    @GetMapping
    public List<Activity> retrieveAll() {
        return activityService.retrieveAllActivities();
    }

    @GetMapping("/{id}")
    public Activity retrieveById(@PathVariable String id) {
        return activityService.findActivityBy(UUID.fromString(id));
    }

    @PostMapping
    public void createActivity(@RequestBody Activity activity) {
        activityService.createActivity(activity);
    }

    @PutMapping(value = "/{id}", produces = MediaType.TEXT_MARKDOWN_VALUE)
    public ResponseEntity<Void> editActivity(@PathVariable String id, @RequestBody Activity activity) {
        activityService.editActivity(UUID.fromString(id), activity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void removeActivity(@PathVariable String id) {
        activityService.removeActivity(UUID.fromString(id));
    }
}
