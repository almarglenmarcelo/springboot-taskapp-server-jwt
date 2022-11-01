package springboot.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import springboot.angular.models.CompletedTask;

import java.util.List;

@Repository
public interface ITaskCompleted extends JpaRepository<CompletedTask, Integer> {
    @Query( value = "SELECT * FROM completed_tasks WHERE user_id = ?1 ", nativeQuery = true)
    List<CompletedTask> findCompletedTasks(int userId);

    @Modifying
    @Query(value = "DELETE FROM completed_tasks WHERE user_id = ?2 AND id = ?1", nativeQuery = true)
    void deleteCompletedTask(int taskId, int userId);
}
