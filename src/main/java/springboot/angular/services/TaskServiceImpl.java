package springboot.angular.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springboot.angular.models.Task;
import springboot.angular.models.User;
import springboot.angular.repository.ITaskRepository;
import springboot.angular.repository.IUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;

@Service
@Transactional
public class TaskServiceImpl implements ITaskService{

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ResponseEntity createTask(Task task, HttpServletRequest httpRequest) {
        HashMap<String, Object> response = new HashMap<>();
        User matchedUser = userRepository.findByUsername(httpRequest.getAttribute("username").toString());

        taskRepository.save(new Task(
                task.getTitle(),
                task.getDescription(),
                matchedUser
        ));
        response.put("result", "task_created");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateTask(Task task, String token) {
        return null;
    }

    @Override
    public ResponseEntity deleteTask(String token) {
        return null;
    }

    @Override
    public ResponseEntity getTask(String token) {
        return null;
    }
}
