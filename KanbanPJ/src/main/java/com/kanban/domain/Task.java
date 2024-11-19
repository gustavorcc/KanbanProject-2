package com.kanban.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity(name = "tasks")
@Table(name = "tasks_tb")

public class Task {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @CreationTimestamp
    private LocalDateTime created_at;

    private LocalDate due_limit_date;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Priority priority;

    public Task(long l, String s, TaskStatus taskStatus) {
        this.id = l;
        this.title = s;
        this.description = s;
    }

    public Task() {}

    @Override
    public String toString() {
        return "\nINFORMAÇÕES DA TAREFA" +
                "\nTítulo: " + this.getTitle() +
                "\nDescrição: " + this.getDescription() +
                "\nData da criação: " + this.getCreated_at() +
                "\nData limite: " + this.getDue_limit_date() +
                "\nStatus: " + this.getStatus() +
                "\nPrioridade: " + this.getPriority();
    }
}