package client.service;

import client.model.Role;
import client.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class UserClient {

    private RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        String url = "http://localhost:8080/allUsers";
        User[] response = restTemplate.getForObject(url, User[].class);
        return Arrays.asList(response);
    }

    public void saveUser(User user) {
        String url = "http://localhost:8080/addUser";
        restTemplate.postForObject(url, user, User.class);
    }

    public User getUserById(Long id) {
        String url = "http://localhost:8080/getOne?id=" + id;
        return restTemplate.getForObject(url, User.class);

    }

    public void editUser(User user) {
        String url = "http://localhost:8080/editUser";
        restTemplate.put(url, user, User.class);
    }

    public void deleteUser(User user) {
        String url = "http://localhost:8080/deleteUser";
        restTemplate.put(url, user, User.class);

    }
}
