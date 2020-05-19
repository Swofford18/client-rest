package client.controller;

import client.model.Role;
import client.model.User;
import client.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {

    private UserServiceImpl userService;

    public MainController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String mainPage(ModelMap model) {


//        String username = principal.getName();
//        User admin = (User) userService.loadUserByUsername(username);


        User admin = new User();
        Set<Role> set = new HashSet<Role>();
        set.add(new Role(1L, "ADMIN"));
        set.add(new Role(2L, "USER"));

        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("pas");
        admin.setEmail("Email");
        admin.setRoles(set);

        model.addAttribute("admin", admin);
        model.addAttribute("user", new User());
        return "admin_page";
    }
}
