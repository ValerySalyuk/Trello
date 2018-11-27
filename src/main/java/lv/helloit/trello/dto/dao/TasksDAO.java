package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.task.Task;

import java.util.List;
import java.util.Optional;

public interface TasksDAO {
    List<Task> getAll();

    Optional<Task> getById(Long id);

    void insert(Task task);

    void delete(Long taskId);

    void update(Long taskId, Task task);
}
