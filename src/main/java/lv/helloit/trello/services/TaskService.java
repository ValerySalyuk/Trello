package lv.helloit.trello.services;

import lv.helloit.trello.dto.dao.TasksDAOImplementation;
import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.task.TaskView;
import lv.helloit.trello.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final UserService userService;
    private final TasksDAOImplementation tasksDAO;

    @Autowired
    public TaskService(UserService userService, TasksDAOImplementation tasksDAO) {
        this.userService = userService;
        this.tasksDAO = tasksDAO;
    }

    public Long addTask(Task task) {
        task.setTaskStatus("To do");
        task.setCreatedDate(new Date());
        return tasksDAO.insert(task);
    }

    private TaskView mapToTaskView(Task task) {
        User user = userService.getUser(task.getAssignedUserId());

        return new TaskView(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedUserId(),
                task.getTaskStatus(),
                task.getCreatedDate(),
                user == null ? null : user.getName() + " " + user.getLastName());
    }

    public List<TaskView> getTasks() {
        return tasksDAO.getAll().stream().map(this::mapToTaskView).collect(Collectors.toList());
    }

    public Task getTask(Long id) {
        Optional<Task> task = tasksDAO.getById(id);

        if (task.isPresent()) {
            return mapToTaskView(task.get());
        } else {
            return null;
        }
    }

    public boolean updateTask(Task newTask) {
        Task oldTask = tasksDAO.getById(newTask.getId()).get();
        newTask.setTaskStatus(oldTask.getTaskStatus());
        newTask.setCreatedDate(oldTask.getCreatedDate());
        newTask.setAssignedUserId(oldTask.getAssignedUserId());
        tasksDAO.update(newTask);
        return true;
    }

    public boolean deleteTask(Long id) {
        if (tasksDAO.getById(id).isPresent()) {
            tasksDAO.delete(id);
            return true;
        }
        return false;
    }

    public boolean assignUser(Long taskId, Long userId) {

        Optional<Task> task = tasksDAO.getById(taskId);

        if (task.isPresent()) {
            Task unwrapped = task.get();
            unwrapped.setAssignedUserId(userId);

            tasksDAO.update(unwrapped);
            return true;
        } else {
            return false;
        }
    }

}
