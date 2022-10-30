package springboot.angular.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.angular.models.Task;
import springboot.angular.services.ITaskService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TaskController {


    @Autowired
    private ITaskService taskService;


    @PostMapping("/api/tasks")
    public ResponseEntity createTask(@RequestBody Task task,  HttpServletRequest httpRequest) {

        return taskService.createTask(task, httpRequest);

    }

}
