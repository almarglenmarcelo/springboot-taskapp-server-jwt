package springboot.angular.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.angular.config.JwtConfig;
import springboot.angular.models.User;
import springboot.angular.repository.IUserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public ResponseEntity userLogin(HashMap<String, Object> credentials) {
        HashMap<String, String> response = new HashMap<>();
        User matchedUser = userRepository.findByUsername(credentials.get("username").toString());

        if(matchedUser != null) {
            String enteredPassword = credentials.get("password").toString();
            boolean isPasswordMatched = new BCryptPasswordEncoder().matches(enteredPassword, matchedUser.getPassword());

            if(isPasswordMatched) {
                response.put("result", "successful");
                response.put("username", matchedUser.getUsername());
                response.put("datetime_registered", matchedUser.getDateTimeRegistered().toString());
                response.put("token", jwtConfig.generateToken(matchedUser.getId(), matchedUser.getUsername()));

                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                response.put("result", "incorrect_credentials");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }else {
            response.put("result", "user_not_found");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity userRegister(User user) {
        HashMap<String, String> response = new HashMap<>();
        User matchedUser = userRepository.findByUsername(user.getUsername());

        if(matchedUser == null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

            User tempUser = new User();
            tempUser.setUsername(user.getUsername());
            tempUser.setPassword(encryptedPassword);
            tempUser.setDateTimeRegistered(LocalDateTime.now());
            userRepository.save(tempUser);

            response.put("result", "user_created_successfully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else{
            response.put("result", "username_already_exists");

            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }


    }
}
