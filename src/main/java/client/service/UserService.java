package client.service;

import client.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    void saveUser(User user);

    User findById(Long id);

    void editUser(User user);

    void delete(User user);
}
