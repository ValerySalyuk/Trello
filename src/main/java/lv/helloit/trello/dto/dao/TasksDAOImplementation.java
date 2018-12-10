package lv.helloit.trello.dto.dao;

import lv.helloit.trello.dto.task.Task;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TasksDAOImplementation extends DAOImplementation<Task> {

    @Autowired
    public TasksDAOImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> getAll() {
        return super.getAll(Task.class);
    }

    public Optional<Task> getById(Long id) {
        return super.getById(id, Task.class);
    }

    public void delete(Long taskId) {
        super.delete(taskId, Task.class);
    }

}
