package lv.helloit.trello.controller;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private TaskController taskController;

    @Autowired
    private UserController userController;

    @Test
    public void shouldCreateTask() {

        Task input = new Task();
        input.setCreatedDate(new Date());
        input.setTitle("Title");
        input.setDescription("Description");

        Long id = taskController.add(input);

        Optional<Task> task = taskController.particularTask(id);

        assertThat(task).isPresent();

        assertThat(task.get().getTitle()).isEqualTo("Title");
        assertThat(task.get().getDescription()).isEqualTo("Description");
        assertThat(task.get().getUser()).isNull();

    }

    @Test
    public void shouldAssignUser() {

        Task task = new Task();
        task.setCreatedDate(new Date());
        task.setTitle("Title");
        task.setDescription("Description");
        Long taskId = taskController.add(task);

        User user = new User();
        user.setName("Name");
        user.setLastName("Last Name");
        user.setAge(25);
        Long userId = userController.add(user);

        Optional<Task> wrappedTask = taskController.particularTask(taskId);
        Optional<User> wrappedUser = userController.particularUser(userId);

        assertThat(wrappedTask).isPresent();
        assertThat(wrappedUser).isPresent();

        taskController.assign(taskId, userId);
        assertThat(wrappedTask.get().getUser()).isNotNull();

    }

}