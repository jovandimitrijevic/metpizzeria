package rs.ac.metropolitan.it355.pz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355.pz.model.Role;
import rs.ac.metropolitan.it355.pz.repository.RoleRepository;
import rs.ac.metropolitan.it355.pz.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleById(int roleID) {
        Optional<Role> roleResult = roleRepository.findById(roleID);

        Role role = null;
        if (roleResult.isPresent()) {
            role = roleResult.get();
        } else {
            throw new RuntimeException("There is no role with id " + roleID);
        }

        return role;
    }
}
