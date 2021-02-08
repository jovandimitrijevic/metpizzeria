package rs.ac.metropolitan.it355.pz.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rs.ac.metropolitan.it355.pz.model.Item;
import rs.ac.metropolitan.it355.pz.model.User;
import rs.ac.metropolitan.it355.pz.service.ItemService;
import rs.ac.metropolitan.it355.pz.service.ProductTypeService;
import rs.ac.metropolitan.it355.pz.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users/items")
public class UserItemController {

    private final UserService userService;
    private final ItemService itemService;
    private final ProductTypeService productTypeService;

    @Autowired
    public UserItemController(UserService userService, ItemService itemService, ProductTypeService productTypeService) {
        this.userService = userService;
        this.itemService = itemService;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/showItems")
    public String getAllItems(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        RestTemplate restTemplate = new RestTemplate();

        final String uriUser = "http://localhost:8080/api/user";
        User user = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = user.getId();

        final String uriItems = "http://localhost:8080/api/users/items";
        List<Item> items = restTemplate.getForObject(uriItems + "/" + userID, List.class);

        int size = items.size();
        model.addAttribute("items", items);
        model.addAttribute("size", size);

        return "shoppingCart";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewItemInShoppingCart(@RequestParam("itemID") int itemID, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        RestTemplate restTemplate = new RestTemplate();

        final String uriUser = "http://localhost:8080/api/user";
        User userObj = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = userObj.getId();

        User user = userService.getUser(userID);
        Item item = itemService.getItemById(itemID);

        int productTypeID = item.getProductType().getId();
        user.addItem(item);
        userService.save(user);
        itemService.saveOrUpdate(item);

        final String uriItems = "http://localhost:8080/api/users/items";
        List<Item> items = restTemplate.getForObject(uriItems + "/" + userID, List.class);

        int size = items.size();
        model.addAttribute("items", items);
        model.addAttribute("size", size);

        return "redirect:/itemsList?id=" + productTypeID;
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteItemFromShoppingCart(@RequestParam("itemID") int itemID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        RestTemplate restTemplate = new RestTemplate();

        final String uriUser = "http://localhost:8080/api/user";
        User userObj = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = userObj.getId();

        User user = userService.getUser(userID);
        Item item = itemService.getItemById(itemID);
        user.removeItem(item);
        userService.save(user);
        itemService.saveOrUpdate(item);

        return "redirect:/users/items/showItems";
    }

    @RequestMapping(value = "/deleteAllItems", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteAllItemsFromShoppingCart(@RequestParam(value = "items", required = false) List<String> items, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        RestTemplate restTemplate = new RestTemplate();

        final String uriUser = "http://localhost:8080/api/user";
        User userObj = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = userObj.getId();

        ObjectMapper objectMapper = new ObjectMapper();

        final String uriItems = "http://localhost:8080/api/users/items";
        items = restTemplate.getForObject(uriItems + "/" + userID, List.class);

        List<Item> itemList = objectMapper.convertValue(items, new TypeReference<List<Item>>() {
        });

        for (Item item : itemList) {
            deleteItemFromShoppingCart(item.getId());
        }

        model.addAttribute("items", itemList);

        return "redirect:/users/items/showItems";
    }

}
