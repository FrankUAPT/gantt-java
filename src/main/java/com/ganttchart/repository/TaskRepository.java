package com.ganttchart.repository;

import com.ganttchart.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this is a Spring Data repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // JpaRepository<Task, Long> means:
    // - This repository will work with the Task entity.
    // - The ID of the Task entity is of type Long.

    // Spring Data JPA will automatically implement methods like:
    // - save(Task entity)
    // - findById(Long id)
    // - findAll()
    // - deleteById(Long id)
    // - etc.

    // You can also define custom query methods here if needed, for example:
    // List<Task> findByAssignee(String assignee);
    // List<Task> findByStartDateBetween(LocalDate start, LocalDate end);
}
