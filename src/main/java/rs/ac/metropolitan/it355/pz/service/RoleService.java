package rs.ac.metropolitan.it355.pz.service;

import rs.ac.metropolitan.it355.pz.model.Role;

public interface RoleService {
    void save(Role role);

    Role getRoleById(int roleID);
}
