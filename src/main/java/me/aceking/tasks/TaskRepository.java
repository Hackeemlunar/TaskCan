package me.aceking.tasks;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Long> {
}