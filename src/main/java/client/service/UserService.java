package client.service;

import client.model.Role;
import client.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserClient userClient;

    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<User> allUsers() {
        return userClient.getAllUsers();
    }

    public void saveUser(User user) {
        userClient.saveUser(user);
    }

    public User findById(Long id) {
        return userClient.getUserById(id);
    }

    public void editUser(User user) {
        userClient.editUser(user);
    }

    public void delete(User user) {
        userClient.deleteUser(user);
    }

}
