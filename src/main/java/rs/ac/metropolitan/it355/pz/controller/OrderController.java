package rs.ac.metropolitan.it355.pz.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import rs.ac.metropolitan.it355.pz.model.*;
import rs.ac.metropolitan.it355.pz.service.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final OrderService orderService;
    private final OrderStatusService orderStatusService;
    private final UserService userService;
    private final ItemService itemService;
    private final UserItemController userItemController;

    @Autowired
    public OrderController(OrderService orderService, OrderStatusService orderStatusService, UserService userService, ItemService itemService, UserItemController userItemController) {
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
        this.userService = userService;
        this.itemService = itemService;
        this.userItemController = userItemController;
    }

    @RequestMapping(value = "/user/order", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmOrder(@RequestParam(value = "items", required = false) List<String> items, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        RestTemplate restTemplate = new RestTemplate();

        final String uriUser = "http://localhost:8080/api/user";
        User user = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = user.getId();

        ObjectMapper objectMapper = new ObjectMapper();

        final String uriItems = "http://localhost:8080/api/users/items";
        items = restTemplate.getForObject(uriItems + "/" + userID, List.class);

        System.out.println(items);

        List<Item> itemList = objectMapper.convertValue(items, new TypeReference<List<Item>>() {
        });

        String itemInfo = "";
        float sum = 0;
        int no = 1;
        for (Item i : itemList) {
            itemInfo += "Item " + no + ": " + i.getName() + " ";
            sum += i.getPrice();
            no++;
        }

        System.out.println("Size: " + itemList.size());

        orderService.save(new Order(user, itemInfo, sum, new OrderStatus(1)));

        model.addAttribute("items", items);

        userItemController.deleteAllItemsFromShoppingCart(items, model);

        return "redirect:/users/items/showItems";
    }

    @GetMapping("/user/viewOrders")
    public String viewOrders(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        final String uriUser = "http://localhost:8080/api/user";
        User user = restTemplate.getForObject(uriUser + "/" + username, User.class);

        int userID = user.getId();
        final String uriOrders = "http://localhost:8080/api/orders";
        List<Order> orders = restTemplate.getForObject(uriOrders + "/" + userID, List.class);

        model.addAttribute("orders", orders);

        /* For showing number of items in shopping cart*/
        final String uriItems = "http://localhost:8080/api/users/items";
        List<Item> items = restTemplate.getForObject(uriItems + "/" + userID, List.class);

        int size = items.size();
        model.addAttribute("items", items);
        model.addAttribute("size", size);

        return "userOrders";
    }

    @GetMapping("/admin/viewAllOrders")
    public String viewAllOrders(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        final String uri = "http://localhost:8080/api/orders";

        List<Order> orders = restTemplate.getForObject(uri, List.class);

        model.addAttribute("orders", orders);

        return "userOrders";
    }

    @GetMapping("/admin/showOrderStatusUpdateForm")
    public String showFormForUpdate(@RequestParam("orderID") int id, Model model) {
        List<OrderStatus> orderStatuses = orderStatusService.getAll();
        Order order = orderService.getOrderById(id);
        OrderStatus orderStatus = new OrderStatus();

        model.addAttribute("order", order);
        model.addAttribute("orderStatuses", orderStatuses);
        model.addAttribute("orderStatus", orderStatus);

        return "updateOrderStatus";
    }

    @PostMapping("/admin/saveStatusUpdate")
    public String saveStatusUpdate(@ModelAttribute("order") Order order) {
        orderService.save(order);
        return "redirect:/admin/viewAllOrders";
    }
}