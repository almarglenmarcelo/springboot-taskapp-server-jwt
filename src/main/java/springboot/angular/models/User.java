package springboot.angular.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private LocalDateTime dateTimeRegistered;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<Task> tasks;

    public User() {}

    public User(String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dateTimeRegistered = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateTimeRegistered() {
        return dateTimeRegistered;
    }

    public void setDateTimeRegistered(LocalDateTime dateTimeRegistered) {
        this.dateTimeRegistered = dateTimeRegistered;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateTimeRegistered=" + dateTimeRegistered +
                '}';
    }
}
