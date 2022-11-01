package springboot.angular.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springboot.angular.models.CompletedTask;
import springboot.angular.models.Task;
import springboot.angular.models.User;
import springboot.angular.repository.ITaskCompleted;
import springboot.angular.repository.ITaskRepository;
import springboot.angular.repository.IUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements ITaskService{

    @Autowired
    private ITaskRepository taskRepository;
    @Autowired
    private IUserRepository userRepository;


    @Override
    public ResponseEntity<Object> createTask(Task task, HttpServletRequest httpRequest) {
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
    public ResponseEntity<Object> updateTask(HashMap<String, String> data, HttpServletRequest httpRequest) {
        HashMap<String, Object> response = new HashMap<>();
        int taskId = Integer.parseInt(data.get("id").toString());
        User matchedUser = userRepository.findByUsername(httpRequest.getAttribute("username").toString());
        String newTitle = data.get("title").toString();
        String newDescription = data.get("description").toString();

        taskRepository.save(new Task(taskId, newTitle, newDescription, matchedUser));

        response.put("result", "task_updated");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> deleteTask(int taskId, HttpServletRequest httpRequest) {
        HashMap<String, Object> response = new HashMap<>();

        int userId = Integer.parseInt(httpRequest.getAttribute("id").toString());
        Optional<Task> tempTask = taskRepository.findById(taskId);

        if(tempTask.isPresent()) {
            taskRepository.deleteTask(tempTask.get().getId(), userId);
            response.put("result", "task_deleted");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {

            response.put("result", "task_not_found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @Override
    public ResponseEntity<Object> getTasks(HttpServletRequest httpRequest) {
        int userId = Integer.parseInt(httpRequest.getAttribute("id").toString());
        List<Task> tasks = taskRepository.findAllTask(userId);


        if(tasks.size() == 0) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("result", "no_tasks_found");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getSingleTask(int taskId, HttpServletRequest httpServletRequest) {
        HashMap<String, Object> response = new HashMap<>();
        int userId = Integer.parseInt(httpServletRequest.getAttribute("id").toString());

        Task tempTask = taskRepository.findTask(taskId, userId);

        if(tempTask != null) {
            return new ResponseEntity<>(tempTask, HttpStatus.OK);
        }else {
            response.put("result", "task_not_found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
