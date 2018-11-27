package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.task.Task;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
public class TasksDAOImplementationTest implements TasksDAO {

    private Task t;

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Optional<Task> getById(Long id) {
        return Optional.ofNullable(t);
    }

    @Override
    public void insert(Task task) {
        t = task;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Long taskId, Task task) {

    }

}