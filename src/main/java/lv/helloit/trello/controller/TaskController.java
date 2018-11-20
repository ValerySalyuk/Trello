package lv.helloit.trello.controller;

import lv.helloit.trello.dto.task.TaskView;
import lv.helloit.trello.services.TaskService;
import lv.helloit.trello.services.UserService;
import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.task.TaskStatus;
import lv.helloit.trello.dto.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public Collection<TaskView> allTasks() {
        LOGGER.info(taskService.getTasks().size() + " tasks returned");
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task particularTask(@PathVariable Long id) {
        LOGGER.info("Returned Task No.: " + id);
        return taskService.getTask(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Task task) {
        taskService.addTask(task);
        LOGGER.info("New task with title: '" + task.getTitle() + "' added");
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Task task) {
        LOGGER.info("Task No.: " + id + " updated");
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        LOGGER.info("Task No.: " + id + " deleted");
        return taskService.deleteTask(id);
    }

    @PutMapping("/assign/{id}")
    public boolean assign(@PathVariable Long id, @RequestParam("userId") Long userId) {
        LOGGER.info("User No. " + userId + " assigned to task No. " + id);
        return taskService.assignUser(id, userId);
    }

    @PutMapping("/setstatus/{id}")
    public boolean setStatus(@PathVariable Long id, @RequestParam("status") String status) {
        LOGGER.info("Added status to task No. " + id);
        return taskService.updateStatus(id, status);
    }

    @GetMapping("/getuser/{id}")
    public User getUser(@PathVariable Long id) {
        LOGGER.info("Returned user assigned to task No. " + id);
        return taskService.getTaskUser(userService, id);
    }

}
