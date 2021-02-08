package rs.ac.metropolitan.it355.pz.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import rs.ac.metropolitan.it355.pz.model.Item;
import rs.ac.metropolitan.it355.pz.model.User;

import java.util.List;

@Controller
public class HomepageController {

    @GetMapping("/index")
    public String home(Model model) {
        if (isUserLoggedIn()) {
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
        }

        return "/index";
    }

    boolean isUserLoggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }

    @RequestMapping(value = "/")
    public String homePage() {
        if (isUserLoggedIn()) {
            return "redirect:/index";
        }
        return "index";
    }

    @GetMapping("/menu")
    public String showGallery() {
        return "gallery";
    }

    @GetMapping("/pageNotFound")
    public String pageNotFound(Model model) {
        if (isUserLoggedIn()) {
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
        }
        return "pageNotFound";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

}

