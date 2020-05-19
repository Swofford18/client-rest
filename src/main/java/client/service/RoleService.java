package client.service;

import client.model.Role;

public interface RoleService {

    Role getAdminRole();

    Role getUserRole();
}
