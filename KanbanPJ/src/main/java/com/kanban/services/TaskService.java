package com.kanban.services;
import com.kanban.domain.Priority;
import com.kanban.domain.Task;
import com.kanban.domain.TaskStatus;
import com.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    public Task save(Task task){
        return repository.save(task);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Task> findAll(){
        return repository.findAll();
    }

    public Task findById(Long id){
        return repository.findById(id).get();
    }

    public TaskStatus nextStatus(TaskStatus currentStatus){
        TaskStatus[] statuses = TaskStatus.values();
        int current = currentStatus.ordinal();

        return statuses[(current + 1) % statuses.length];
    }

    public List<Task> getAllGrouped(){
        List<Task> tasks = findAll();

        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus)
                        .thenComparing(Comparator.comparing(Task::getPriority).reversed())
                        .thenComparing(Task::getDue_limit_date))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        List<Task> tasks = findAll();
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .sorted(Comparator.comparing(Task::getPriority).reversed()
                        .thenComparing(Task::getDue_limit_date))
                .collect(Collectors.toList());
    }
}