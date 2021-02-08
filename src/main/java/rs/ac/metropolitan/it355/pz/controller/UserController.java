package rs.ac.metropolitan.it355.pz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import rs.ac.metropolitan.it355.pz.model.Role;
import rs.ac.metropolitan.it355.pz.model.User;
import rs.ac.metropolitan.it355.pz.service.RoleService;
import rs.ac.metropolitan.it355.pz.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        User user = new User();
        //RestTemplate restTemplate = new RestTemplate();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/save")
    public String addUser(final ModelMap model, @Valid @ModelAttribute("user") final User user, final BindingResult bindingResult) {

        System.out.println(bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        user.setEnabled(1);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleService.getRoleById(2);
        user.addRole(role);
        userService.save(user);

        return "redirect:/login";
    }
}
