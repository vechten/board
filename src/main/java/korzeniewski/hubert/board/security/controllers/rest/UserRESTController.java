package korzeniewski.hubert.board.security.controllers.rest;

import korzeniewski.hubert.board.security.model.User;
import korzeniewski.hubert.board.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller which operates with User model class.
 */
@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserRESTController {

    private UserRepository userRepository;

    @Autowired
    public UserRESTController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Sends back information about all users.
     *
     * @return all users from database.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

}
