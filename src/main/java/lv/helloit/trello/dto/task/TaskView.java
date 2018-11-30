package lv.helloit.trello.dto.task;

import java.time.LocalDateTime;
import java.util.Date;

public class TaskView extends Task {

    public String userName;

    public TaskView() {
    }

    public TaskView(String userName) {
        this.userName = userName;
    }

    public TaskView(Long id, String title, String description, Long assignedUserId, String taskStatus, Date createdDate, String userName) {
        super(id, title, description, assignedUserId, taskStatus, createdDate);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
