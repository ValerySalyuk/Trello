package lv.helloit.trello.services;

import lv.helloit.trello.dto.dao.TasksDAOImplementation;
import lv.helloit.trello.dto.dao.UsersDAOImplementation;
import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TasksDAOImplementation tasksDAOImplementation;
    private final UsersDAOImplementation usersDAOImplementation;

    @Autowired
    public TaskService(TasksDAOImplementation tasksDAOImplementation, UsersDAOImplementation usersDAOImplementation) {
        this.tasksDAOImplementation = tasksDAOImplementation;
        this.usersDAOImplementation = usersDAOImplementation;
    }

    public Long addTask(Task task) {
        task.setTaskStatus("To do");
        task.setCreatedDate(new Date());
        return tasksDAOImplementation.insert(task);
    }

    public List<Task> getTasks() {
        return tasksDAOImplementation.getAll();
    }

    public Optional<Task> getTask(Long id) {
        return tasksDAOImplementation.getById(id);
    }

    public boolean updateTask(Task newTask) {
        Task oldTask = tasksDAOImplementation.getById(newTask.getId()).get();
        newTask.setTaskStatus(oldTask.getTaskStatus());
        newTask.setCreatedDate(oldTask.getCreatedDate());
        tasksDAOImplementation.update(newTask);
        return true;
    }

    public boolean deleteTask(Long id) {
        if (tasksDAOImplementation.getById(id).isPresent()) {
            tasksDAOImplementation.delete(id);
            return true;
        }
        return false;
    }

    public boolean assignUser(Long taskId, Long userId) {

        Optional<Task> wrappedTask = this.getTask(taskId);
        Optional<User> wrappedUser = usersDAOImplementation.getById(userId);

        if (wrappedTask.isPresent() && wrappedUser.isPresent()) {
            Task task = wrappedTask.get();
            task.setUser(wrappedUser.get());

            this.updateTask(task);
            return true;
        }

        return false;
    }

    public boolean unassignUser(Long taskId) {

        Optional<Task> wrappedTask = this.getTask(taskId);

        if (wrappedTask.isPresent()) {
            Task task = wrappedTask.get();
            task.setUser(null);
            tasksDAOImplementation.update(task);
            return true;
        }

        return false;

    }

}
