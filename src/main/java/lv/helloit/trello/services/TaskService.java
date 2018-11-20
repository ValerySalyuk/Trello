package lv.helloit.trello.services;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.task.TaskStatus;
import lv.helloit.trello.dto.task.TaskView;
import lv.helloit.trello.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TaskService {

    @Autowired
    private UserService userService;

    private Map<Long, Task> taskMap = new HashMap<>();
    private Long lastId = 0L;

    public Long addTask(Task task) {
        task.setId(++lastId);
        task.setTaskStatus(TaskStatus.TODO);
        taskMap.put(lastId, task);
        return lastId;
    }

    public boolean taskExists(Long id) {
        return taskMap.containsKey(id);
    }

    public List<TaskView> getTasks() {
        return taskMap.values().stream().map(task -> {
            User user = userService.getUser(task.getAssignedUserId());

            return new TaskView(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getAssignedUserId(),
                    task.getTaskStatus(),
                    user == null ? null : user.getName() + " " + user.getLastName()
            );
        }).collect(Collectors.toList());
    }

    public Task getTask(Long id) {
        if (taskExists(id)) {
            return taskMap.get(id);
        } else {
            return null;
        }
    }

    public boolean updateTask(Long id, Task task) {
        if (taskExists(id)) {
            task.setId(id);
            task.setTaskStatus(taskMap.get(id).getTaskStatus());
            taskMap.replace(id, task);
            return true;
        }
        return false;
    }

    public boolean deleteTask(Long id) {
        if (taskExists(id)) {
            taskMap.remove(id);
            return true;
        }
        return false;
    }

    public boolean assignUser(Long taskId, Long userId) {
        if (taskExists(taskId) && userService.userExists(userId)) {
            taskMap.get(taskId).setAssignedUserId(userId);
            return true;
        }
        return false;
    }

    public User getTaskUser(UserService userService, Long taskId) {
        if (taskExists(taskId)) {
            return userService.getUser(taskMap.get(taskId).getAssignedUserId());
        } else {
            return null;
        }

    }

    public boolean updateStatus(Long taskId, String newStatus) {
        if (taskExists(taskId) && TaskStatus.contains(newStatus)) {
            taskMap.get(taskId).setTaskStatus(TaskStatus.valueOf(newStatus.toUpperCase()));
            return true;
        }
        return false;
    }

}
