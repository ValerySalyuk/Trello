package lv.helloit.trello.controller;

import lv.helloit.trello.dto.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private TaskController taskController;

    @Test
    public void shouldCreateTask() {

        taskController.add(new Task(null, "Title", "Description", null, null));

        Task task = taskController.particularTask(1L);

        assertThat(task.getTitle()).isEqualTo("Title");
        assertThat(task.getDescription()).isEqualTo("Description");
        assertThat(task.getAssignedUserId()).isNull();

    }

}