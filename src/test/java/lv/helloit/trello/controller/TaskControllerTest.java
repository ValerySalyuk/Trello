package lv.helloit.trello.controller;

import lv.helloit.trello.dto.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {

    @Autowired
    private TaskController taskController;

    @Test
    public void shouldCreateTask() {

        Long newId = taskController.add(new Task(null, "Title", "Description", null, null, null));

        Task task = taskController.particularTask(newId);

        assertThat(task.getTitle()).isEqualTo("Title");
        assertThat(task.getDescription()).isEqualTo("Description");
        assertThat(task.getAssignedUserId()).isNull();

    }

}