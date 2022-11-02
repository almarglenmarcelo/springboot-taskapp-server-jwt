package springboot.angular.services;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ITaskCompletedService {


    //    Completed Task Methods
    ResponseEntity<Object> getCompletedTasks(HttpServletRequest httpServletRequest);

    ResponseEntity<Object> taskCompleted(HashMap<String, Object> data, HttpServletRequest httpServletRequest);

    ResponseEntity<Object> deleteTaskCompleted(int taskId, HttpServletRequest httpServletRequest);

    ResponseEntity<Object> deleteTask(int taskId, HttpServletRequest httpRequest);

    ResponseEntity<Object> redoTask(HashMap<String, Object> data, HttpServletRequest httpRequest);
}
