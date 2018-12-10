package lv.helloit.trello.dto.task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lv.helloit.trello.dto.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "VS_TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

//    @Column(name = "assigned_user_id")
//    private Long assignedUserId;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    @JsonBackReference
    private User user;

    @Column(name = "status")
    private String taskStatus;
    @Column(name = "created_date")
    private Date createdDate;

    public Task() {
    }

    public Task(String title, String description, User user, String taskStatus, Date createdDate) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.taskStatus = taskStatus;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + (user != null ? user.getId() : "") +
                ", taskStatus='" + taskStatus + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(user.getId(), task.user.getId()) &&
                Objects.equals(taskStatus, task.taskStatus) &&
                Objects.equals(createdDate, task.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, user.getId(), taskStatus, createdDate);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Long getAssignedUserId() {
//        return assignedUserId;
//    }

//    public void setAssignedUserId(Long assignedUserId) {
//        this.assignedUserId = assignedUserId;
//    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
