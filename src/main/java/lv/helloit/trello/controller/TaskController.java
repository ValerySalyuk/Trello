package lv.helloit.trello.controller;

import lv.helloit.trello.dto.task.TaskView;
import lv.helloit.trello.services.TaskService;
import lv.helloit.trello.services.UserService;
import lv.helloit.trello.dto.task.Task;
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

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
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
    public Long add(@RequestBody Task task) {
        LOGGER.info("New task with title: '" + task.getTitle() + "' added");
        return taskService.addTask(task);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable Long id, @RequestBody Task task) {
        LOGGER.info("Task No.: " + id + " updated");
        task.setId(id);
        return taskService.updateTask(task);
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

}
