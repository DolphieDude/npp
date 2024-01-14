package ua.kpi.npp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.npp.entity.Role;
import ua.kpi.npp.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> getRolesWithDescriptionContaining(String keyword) {
        return roleRepository.findByDescriptionContaining(keyword);
    }

    public List<Role> getAllRolesOrderedByName() {
        return roleRepository.findByOrderByNameAsc();
    }

    public List<Role> searchRolesByName(String keyword) {
        return roleRepository.searchRoles(keyword);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRoleById(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
