package lv.helloit.trello.dto.task;

public class TaskView extends Task {

    public String userName;

    public TaskView() {
    }

    public TaskView(String userName) {
        this.userName = userName;
    }

    public TaskView(Long id, String title, String description, Long assignedUserId, TaskStatus taskStatus, String userName) {
        super(id, title, description, assignedUserId, taskStatus);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
