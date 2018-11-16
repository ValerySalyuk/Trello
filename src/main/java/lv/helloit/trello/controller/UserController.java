package lv.helloit.trello.controller;

import lv.helloit.trello.services.UserService;
import lv.helloit.trello.dto.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        LOGGER.info("User Service wired to User Controller");
    }

    @GetMapping
    public Collection<User> allUsers() {
        LOGGER.info(userService.userList().size() + " users returned");
        return userService.userList();
    }

    @GetMapping("/{id}")
    public User particularUser(@PathVariable Long id) {
        LOGGER.info("Request for user No.: " + id);
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userService.addUser(user);
        LOGGER.info("New user " + user.getName() + " " + user.getLastName() + " added");
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
        LOGGER.info("Request for user No.: " + id + " deletion");
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        LOGGER.info("Request for user No.: " + id + " update");
    }

}
