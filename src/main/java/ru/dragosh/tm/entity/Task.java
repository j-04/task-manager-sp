package ru.dragosh.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumarate.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    private String id = UUID.randomUUID().toString();
    @Nullable private String name;
    @Nullable private String description;
    @Nullable private Date dateStart;
    @Nullable private Date dateFinish;
    @Nullable private String projectId;
    @Enumerated(value = EnumType.STRING)
    @Nullable private Status status = Status.SCHEDULED;

    public Task (
            @Nullable final String name,
            @Nullable final String description,
            @Nullable final Date dateStart,
            @Nullable final Date dateFinish,
            @Nullable final String projectId,
            @Nullable final Status status
    ) {
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.projectId = projectId;
        this.status = status;
    }
}
