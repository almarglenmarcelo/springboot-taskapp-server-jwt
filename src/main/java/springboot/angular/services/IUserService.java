package springboot.angular.services;

import org.springframework.http.ResponseEntity;
import springboot.angular.models.User;

import java.util.HashMap;

public interface IUserService {

    ResponseEntity userLogin(HashMap<String, Object> credentials);

    ResponseEntity userRegister(User user);
}
