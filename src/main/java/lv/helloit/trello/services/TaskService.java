package lv.helloit.trello.services;

import lv.helloit.trello.dto.dao.TasksDAO;
import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.task.TaskStatus;
import lv.helloit.trello.dto.task.TaskView;
import lv.helloit.trello.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TaskService {

    private final UserService userService;
    private final TasksDAO tasksDAO;

    @Autowired
    public TaskService(UserService userService, TasksDAO tasksDAO) {
        this.userService = userService;
        this.tasksDAO = tasksDAO;
    }

    public void addTask(Task task) {
        task.setTaskStatus(TaskStatus.TODO);
        tasksDAO.insert(task);
    }

//    public boolean taskExists(Long id) {
//        return taskMap.containsKey(id);
//    }

    private TaskView mapToTaskView(Task task) {
        User user = userService.getUser(task.getAssignedUserId());

        return new TaskView(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getAssignedUserId(),
                task.getTaskStatus(),
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

    public boolean updateTask(Long taskId, Task newTask) {
        if (!taskId.equals(newTask.getId()) && newTask.getId() != null) {
            return false;
        }

        Optional<Task> oldTask = tasksDAO.getById(taskId);
        if (oldTask.isPresent()) {
            newTask.setTaskStatus(oldTask.get().getTaskStatus());
            tasksDAO.update(taskId, newTask);
        }
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

            tasksDAO.update(taskId, unwrapped);
            return true;
        } else {
            return false;
        }
    }

//    public User getTaskUser(UserService userService, Long taskId) {
//        if (taskExists(taskId)) {
//            return userService.getUser(taskMap.get(taskId).getAssignedUserId());
//        } else {
//            return null;
//        }
//
//    }

//    public boolean updateStatus(Long taskId, String newStatus) {
//        if (taskExists(taskId) && TaskStatus.contains(newStatus)) {
//            taskMap.get(taskId).setTaskStatus(TaskStatus.valueOf(newStatus.toUpperCase()));
//            return true;
//        }
//        return false;
//    }

}
