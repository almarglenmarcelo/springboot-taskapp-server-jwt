package springboot.angular.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.angular.models.Task;
import springboot.angular.services.ITaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class TaskController {


    @Autowired
    private ITaskService taskService;


    @GetMapping("/api/tasks")
    public ResponseEntity getTasks( HttpServletRequest httpServletRequest){

        return taskService.getTasks(httpServletRequest);
    }
    @GetMapping("/api/tasks/{taskId}")
    public ResponseEntity<Object> getTask(@PathVariable int taskId, HttpServletRequest httpRequest){
        return taskService.getSingleTask(taskId, httpRequest);
    }

    @PostMapping("/api/tasks")
    public ResponseEntity createTask(@RequestBody Task task,  HttpServletRequest httpRequest) {

        return taskService.createTask(task, httpRequest);

    }
    @PutMapping("/api/tasks")
    public ResponseEntity<Object> updateTask(@RequestBody HashMap<String, String> data, HttpServletRequest httpServletRequest){

        return taskService.updateTask(data, httpServletRequest);
    }

    @DeleteMapping("/api/tasks/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable int taskId, HttpServletRequest httpServletRequest){
        return taskService.deleteTask(taskId, httpServletRequest);
    }

}
