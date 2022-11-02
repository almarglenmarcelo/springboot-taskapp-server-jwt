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
public class TaskCompletedServiceImpl implements ITaskCompletedService {

    @Autowired
    private ITaskCompleted taskCompletedRepository;

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ResponseEntity<Object> deleteTaskCompleted(int taskId, HttpServletRequest httpServletRequest) {
        HashMap<String, Object> response = new HashMap<>();

        int userId = Integer.parseInt(httpServletRequest.getAttribute("id").toString());
        Optional<CompletedTask> tempTask = taskCompletedRepository.findById(taskId);

        if(tempTask.isPresent()) {
            taskCompletedRepository.deleteCompletedTask(tempTask.get().getId(), userId);
            response.put("result", "task_deleted");

            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {

            response.put("result", "task_not_found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<Object> getCompletedTasks(HttpServletRequest httpServletRequest) {
        HashMap<String, Object> response = new HashMap<>();
        int userId = Integer.parseInt(httpServletRequest.getAttribute("id").toString());
        List<CompletedTask> completedTasks = taskCompletedRepository.findCompletedTasks(userId);

        System.out.println(userId);

        if(completedTasks.size() == 0){
            response.put("result", "no_completed_tasks_found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(completedTasks, HttpStatus.OK);

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
    public ResponseEntity<Object> taskCompleted(HashMap<String, Object> data, HttpServletRequest httpRequest) {
        HashMap<String, Object> response = new HashMap<>();
        int userId = Integer.parseInt(httpRequest.getAttribute("id").toString());
        int taskId = Integer.parseInt(data.get("id").toString());
        Optional<Task> tempTask = taskRepository.findById(taskId);

        Task theTask = null;
        if(tempTask.isPresent()){
            theTask = tempTask.get();

            CompletedTask completedTask = new CompletedTask(theTask.getId(),
                    theTask.getTitle(),
                    theTask.getDescription(),
                    userId,
                    LocalDateTime.now());
            taskCompletedRepository.save(completedTask);
            deleteTask(taskId, httpRequest);
            response.put("result", "task_finished");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("result", "task_not_found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> redoTask(HashMap<String, Object> data, HttpServletRequest httpRequest) {
        HashMap<String, Object> response = new HashMap<>();

        int completedTaskId = Integer.parseInt(data.get("id").toString());
        String username = httpRequest.getAttribute("username").toString();
        User matchedUser = userRepository.findByUsername(username);

        CompletedTask completedTask = taskCompletedRepository.findById(completedTaskId).get();

        if(completedTask != null) {
            Task theTask = new Task();
            theTask.setTitle(completedTask.getTitle());
            theTask.setDescription(completedTask.getDescription());
            theTask.setUser(matchedUser);

            taskRepository.save(theTask);
            taskCompletedRepository.deleteById(completedTaskId);
            response.put("result", "task_redo");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.put("result", "completed_task_not_found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
