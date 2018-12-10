package lv.helloit.trello.services;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Test
    public void shouldUnassignUser() {

        Task task = new Task();
        task.setCreatedDate(new Date());
        task.setTitle("Title");
        task.setDescription("Description");
        Long taskId = taskService.addTask(task);

        User user = new User();
        user.setName("Name");
        user.setLastName("Last Name");
        user.setAge(25);
        Long userId = userService.addUser(user);

        taskService.assignUser(taskId, userId);
        Task taskFromDBWithUser = taskService.getTask(taskId).get();
        assertThat(taskFromDBWithUser.getUser()).isNotNull();

        taskService.unassignUser(taskId);
        Task taskFromDBNoUser = taskService.getTask(taskId).get();
        assertThat(taskFromDBNoUser.getUser()).isNull();

    }

}