package com.app.reactivities.model.entity;

import com.app.reactivities.util.LocalDateTimeDeserializer;
import com.app.reactivities.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
public class Activity {
    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(nullable = false, length = 100, columnDefinition = "varchar")
    private String title;
//    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false, length = 500, columnDefinition = "varchar")
    private String description;
    private String category;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String venue;
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @CreationTimestamp
    @JsonIgnore
    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedDateTime;
}
