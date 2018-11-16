package lv.helloit.trello;

import lv.helloit.trello.dto.task.Task;
import lv.helloit.trello.dto.user.User;
import lv.helloit.trello.services.TaskService;
import lv.helloit.trello.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrelloApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TrelloApplication.class, args);

		TaskService ts = context.getBean(TaskService.class);
		ts.addTask(new Task(null, "Task 1", "Test task 1 description", null));
		ts.addTask(new Task(null, "Task 2", "Test task 2 description", null));
		ts.addTask(new Task(null, "Task 3", "Test task 3 description", null));
		ts.addTask(new Task(null, "Task 4", "Test task 4 description", null));
		ts.addTask(new Task(null, "Task 5", "Test task 5 description", null));

		UserService us = context.getBean(UserService.class);
		us.addUser(new User(null, 23, "Marija", "Ostrova"));
		us.addUser(new User(null, 27, "Darja", "Petrova"));
		us.addUser(new User(null, 33, "Svetlana", "Sinicina"));
		us.addUser(new User(null, 31, "Olga", "Petuhova"));
		us.addUser(new User(null, 25, "Natalja", "Durova"));

	}
}
