package rs.ac.metropolitan.it355.pz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.it355.pz.model.User;
import rs.ac.metropolitan.it355.pz.service.ItemService;
import rs.ac.metropolitan.it355.pz.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRESTController {

    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public UserRESTController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/userById/{username}")
    public boolean getUser1(@PathVariable String username) {
        return userService.isUsernameAlreadyInUse(username);
    }
}
