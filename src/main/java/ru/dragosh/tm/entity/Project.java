package ru.dragosh.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumarate.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @NotNull private String id = UUID.randomUUID().toString();
    @Nullable private String name;
    @Nullable private String description;
    @Nullable private Date dateStart;
    @Nullable private Date dateFinish;
    @Enumerated(value = EnumType.STRING)
    @NotNull private Status status = Status.SCHEDULED;

    public Project(@NotNull final String name,
                   @NotNull final String description,
                   @NotNull final Date dateStart,
                   @NotNull final Date dateFinish,
                   @NotNull final Status status
    ) {
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
    }
}
