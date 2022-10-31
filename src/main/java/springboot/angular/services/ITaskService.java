package springboot.angular.services;


import org.springframework.http.ResponseEntity;
import springboot.angular.models.Task;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ITaskService {

    ResponseEntity  createTask(Task task, HttpServletRequest httpRequest);

    ResponseEntity updateTask(HashMap<String, String> data, HttpServletRequest httpRequest);

    ResponseEntity getSingleTask(int taskId, HttpServletRequest httpServletRequest);

    ResponseEntity deleteTask(int taskId, HttpServletRequest httpRequest);

    ResponseEntity getTasks(HttpServletRequest httpRequest);

}
