package client.service;

import client.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    public Role getAdminRole() {
        return new Role(1L, "ROLE_ADMIN");
    }

    public Role getUserRole() {
        return new Role(2L, "ROLE_USER");
    }
}
