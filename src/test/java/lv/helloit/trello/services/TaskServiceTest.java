//package lv.helloit.trello.services;
//
//import lv.helloit.trello.dto.task.Task;
//import lv.helloit.trello.dto.task.TaskStatus;
//import lv.helloit.trello.dto.user.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.jws.soap.SOAPBinding;
//
//import static lv.helloit.trello.dto.task.TaskStatus.DONE;
//import static lv.helloit.trello.dto.task.TaskStatus.TODO;
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.nullable;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TaskServiceTest {
//
//    private final Long TASK_ID = 1L;
//    private final Long USER_ID = 1L;
//    private Task task;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private TaskService victim;
//
//    @Before
//    public void serUp() {
//        task = new Task(null, "Task 1", "Test task",  null);
//        when(userService.userExists(USER_ID)).thenReturn(true);
//    }
//
//    @Test
//    public void shouldAddNewTask() {
//
//        Long taskId = victim.addTask(task);
//        assertEquals(TASK_ID, taskId);
//
//    }
//
//    @Test
//    public void taskShouldExist() {
//
//        assertFalse(victim.taskExists(TASK_ID));
//        victim.addTask(task);
//        assertTrue(victim.taskExists(TASK_ID));
//
//    }
//
//    @Test
//    public void shouldReturnTask() {
//
//        assertNull(victim.getTask(TASK_ID));
//        victim.addTask(task);
//        assertNotNull(victim.getTask(TASK_ID));
//
//    }
//
//    @Test
//    public void shouldUpdateTask() {
//        victim.addTask(task);
//        Task task2 = new Task(null, "Task updated", "Updated task", null);
//        victim.updateTask(TASK_ID, task2);
//        assertEquals("Updated task" , victim.getTask(TASK_ID).getDescription());
//    }
//
//    @Test
//    public void shouldDeleteTask() {
//        assertFalse(victim.taskExists(TASK_ID));
//        victim.addTask(task);
//        assertTrue(victim.taskExists(TASK_ID));
//        victim.deleteTask(TASK_ID);
//        assertFalse(victim.taskExists(TASK_ID));
//    }
//
//    @Test
//    public void shouldAssignUser() {
//
//        victim.addTask(task);
//        victim.assignUser(TASK_ID, USER_ID);
//        assertEquals(USER_ID, victim.getTask(TASK_ID).getAssignedUserId());
//
//    }
//
//    @Test
//    public void shouldGetTaskUser() {
//        victim.addTask(task);
//        victim.assignUser(TASK_ID, USER_ID);
//        assertEquals(USER_ID, victim.getTask(TASK_ID).getAssignedUserId());
//        assertNull(victim.getTaskUser(userService, TASK_ID));
//    }
//
//    @Test
//    public void shouldUpdateStatus() {
//        victim.addTask(task);
//        victim.updateStatus(TASK_ID, "hello");
//        assertEquals(TODO, victim.getTask(TASK_ID).getTaskStatus());
//        victim.updateStatus(TASK_ID, "DoNE");
//        assertEquals(TaskStatus.DONE, victim.getTask(TASK_ID).getTaskStatus());
//    }
//
//}