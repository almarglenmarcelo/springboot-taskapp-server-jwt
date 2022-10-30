package springboot.angular.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.angular.models.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {
}
