package client.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    public User() {
    }
}
