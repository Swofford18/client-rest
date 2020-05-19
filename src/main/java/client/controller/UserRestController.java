package client.controller;

import client.model.Role;
import client.model.User;
import client.service.RoleServiceImpl;
import client.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserRestController {

    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    public UserRestController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        userService.saveUser(roleDefinition(user));
        return user;
    }

    @GetMapping("/allUsers")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userService.findById(id);
    }

    @PutMapping("/editUser")
    public User editUser(@RequestBody User user) {

        userService.editUser(roleDefinition(user));
        return user;
    }

    @DeleteMapping("/deleteUser")
    public User deleteUser(@RequestBody User user) {

        userService.delete(user);
        return user;
    }

    private User roleDefinition(User user) {
        Role adminRole = roleService.getAdminRole();
        Role userRole = roleService.getUserRole();

        Set<Role> newUserRoles = new HashSet<>();

        for (Role role : user.getRoles()) {

            if (role.getName().equals("ROLE_ADMIN")) {
                newUserRoles.add(adminRole);
            }
            if (role.getName().equals("ROLE_USER")) {
                newUserRoles.add(userRole);
            }

            user.setRoles(newUserRoles);
        }
        return user;
    }
}
