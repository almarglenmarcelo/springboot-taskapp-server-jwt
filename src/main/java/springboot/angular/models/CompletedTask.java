package springboot.angular.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="completed_tasks")
public class CompletedTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private int userId;

    @Column
    private LocalDateTime datetime_completed;


    public CompletedTask(){}

    public CompletedTask(int id, String title, String description, int userId, LocalDateTime datetime_completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.datetime_completed = datetime_completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDatetime_completed() {
        return datetime_completed;
    }

    public void setDatetime_completed(LocalDateTime datetime_completed) {
        this.datetime_completed = datetime_completed;
    }

    @Override
    public String toString() {
        return "CompletedTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", datetime_completed=" + datetime_completed +
                '}';
    }
}
