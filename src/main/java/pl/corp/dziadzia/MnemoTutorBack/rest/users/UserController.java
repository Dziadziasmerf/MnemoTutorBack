package pl.corp.dziadzia.MnemoTutorBack.rest.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.corp.dziadzia.MnemoTutorBack.model.User;
import pl.corp.dziadzia.MnemoTutorBack.repo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserPresentation>> getUsers() {

        List<UserPresentation> users = userRepository.findAll()
                .stream()
                .map(UserPresentation::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserPresentation> getUser(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(new UserPresentation(userRepository.getOne(id)), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity updateUser(@RequestBody UserPresentation userPresentation, @PathVariable("id") Integer id) {
        User user = userRepository.findUserById(id);
        user.setEmail(userPresentation.getEmail());

        userRepository.save(user);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.HEAD)
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        try {
            User user = userRepository.findUserByUsername(username);
            return user != null ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error: ", e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
