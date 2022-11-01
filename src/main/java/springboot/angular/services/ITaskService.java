package springboot.angular.services;


import org.springframework.http.ResponseEntity;
import springboot.angular.models.Task;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ITaskService {

    ResponseEntity<Object>  createTask(Task task, HttpServletRequest httpRequest);

    ResponseEntity<Object> updateTask(HashMap<String, String> data, HttpServletRequest httpRequest);

    ResponseEntity<Object> getSingleTask(int taskId, HttpServletRequest httpServletRequest);

    ResponseEntity<Object> deleteTask(int taskId, HttpServletRequest httpRequest);

    ResponseEntity<Object> getTasks(HttpServletRequest httpRequest);





}
