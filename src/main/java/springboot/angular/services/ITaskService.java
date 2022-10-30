package springboot.angular.services;


import org.springframework.http.ResponseEntity;
import springboot.angular.models.Task;

import javax.servlet.http.HttpServletRequest;

public interface ITaskService {

    ResponseEntity  createTask(Task task, HttpServletRequest httpRequest);

    ResponseEntity updateTask(Task task, String token);

    ResponseEntity deleteTask(String token);

    ResponseEntity getTask(String token);

}
