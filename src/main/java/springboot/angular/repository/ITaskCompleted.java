package springboot.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.angular.models.CompletedTask;

@Repository
public interface ITaskCompleted extends JpaRepository<CompletedTask, Integer> {
}
