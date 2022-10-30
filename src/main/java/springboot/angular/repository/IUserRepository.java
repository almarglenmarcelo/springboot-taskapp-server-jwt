package springboot.angular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.angular.models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);

}
