package springboot.angular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.angular.repository.ITaskCompleted;
import springboot.angular.services.ITaskCompletedService;
import springboot.angular.services.ITaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class TaskCompletedController {

    @Autowired
    private ITaskCompletedService taskCompletedService;


    @GetMapping("/api/tasks/completed")
    public ResponseEntity<Object> getCompletedTasks(HttpServletRequest httpServletRequest){
        return taskCompletedService.getCompletedTasks(httpServletRequest);
    }

    @PostMapping("/api/tasks/complete")
    public ResponseEntity<Object> completeTask(@RequestBody HashMap<String, Object> data, HttpServletRequest httpRequest){

        return taskCompletedService.taskCompleted(data, httpRequest);
    }

    @DeleteMapping("/api/tasks/completed/{taskId}")
    public ResponseEntity<Object> deleteCompletedTask(@PathVariable int taskId, HttpServletRequest httpServletRequest){
        return taskCompletedService.deleteTaskCompleted(taskId, httpServletRequest);
    }

}
