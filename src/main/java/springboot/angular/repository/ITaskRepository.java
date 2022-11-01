package springboot.angular.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.angular.models.Task;
import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1", nativeQuery = true)
    List<Task> findAllTask(int userId);

    @Query(value = "SELECT * FROM tasks WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    Task findTask(int taskId, int userId);


    @Modifying
    @Query(value = "DELETE FROM tasks WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    void deleteTask(int taskId, int userId);


}
