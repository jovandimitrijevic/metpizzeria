package rs.ac.metropolitan.it355.pz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.it355.pz.model.Item;
import rs.ac.metropolitan.it355.pz.model.User;
import rs.ac.metropolitan.it355.pz.model.UserItem;
import rs.ac.metropolitan.it355.pz.service.ItemService;
import rs.ac.metropolitan.it355.pz.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserItemRESTController {

    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public UserItemRESTController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("users/items/{userID}")
    public List<Item> items(@PathVariable int userID) {
        User user = userService.getUser(userID);
        return user.getItems();
    }

    @PostMapping("users/items/{userID}/{itemID}")
    public List<Item> items(@PathVariable int userID, @PathVariable int itemID) {
        User user = userService.getUser(userID);
        Item item = itemService.getItemById(itemID);
        user.addItem(item);
        userService.save(user);
        itemService.saveOrUpdate(item);
        return user.getItems();
    }

    @PutMapping("user/a")
    public String deleteItems(@RequestBody UserItem userItem) {
        User user = userService.getUser(userItem.getUserID());
        Item item = itemService.getItemById(userItem.getItemID());
        user.removeItem(item);
        userService.save(user);
        itemService.saveOrUpdate(item);
        return "Success";
    }
}
