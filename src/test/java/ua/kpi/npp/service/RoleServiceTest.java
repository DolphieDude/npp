package ua.kpi.npp.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.kpi.npp.entity.Role;
import ua.kpi.npp.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void testGetRoleByName() {
        String roleName = "TEST_ROLE";
        Role expectedRole = new Role();
        expectedRole.setId(1L);
        expectedRole.setName(roleName);

        when(roleRepository.findByName(roleName)).thenReturn(expectedRole);

        Role resultRole = roleService.getRoleByName(roleName);

        assertEquals(expectedRole, resultRole);
        verify(roleRepository, times(1)).findByName(roleName);
    }

    @Test
    public void testGetRolesWithDescriptionContaining() {
        String keyword = "keyword";
        Role role1 = new Role();
        role1.setId(1L);
        role1.setDescription("Description containing keyword");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setDescription("Another description");

        when(roleRepository.findByDescriptionContaining(keyword)).thenReturn(Arrays.asList(role1, role2));

        List<Role> resultRoles = roleService.getRolesWithDescriptionContaining(keyword);

        assertEquals(2, resultRoles.size());
        verify(roleRepository, times(1)).findByDescriptionContaining(keyword);
    }

    @Test
    public void testGetAllRolesOrderedByName() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("B_ROLE");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("A_ROLE");

        when(roleRepository.findByOrderByNameAsc()).thenReturn(Arrays.asList(role2, role1));

        List<Role> resultRoles = roleService.getAllRolesOrderedByName();

        assertEquals("A_ROLE", resultRoles.get(0).getName());
        assertEquals("B_ROLE", resultRoles.get(1).getName());
        verify(roleRepository, times(1)).findByOrderByNameAsc();
    }

    @Test
    public void testSearchRolesByName() {
        String keyword = "TEST_ROLE_";
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("TEST_ROLE_1");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("TEST_ROLE_2");

        when(roleRepository.searchRoles(keyword)).thenReturn(Arrays.asList(role1, role2));

        List<Role> resultRoles = roleService.searchRolesByName(keyword);

        assertEquals(2, resultRoles.size());
        assertTrue(resultRoles.stream().allMatch(r -> r.getName().startsWith(keyword)));
        verify(roleRepository, times(1)).searchRoles(keyword);
    }

    @Test
    public void testSaveRole() {
        Role roleToSave = new Role();
        roleToSave.setName("NEW_ROLE");

        Role savedRole = new Role();
        savedRole.setId(1L);
        savedRole.setName("NEW_ROLE");

        when(roleRepository.save(roleToSave)).thenReturn(savedRole);

        Role resultRole = roleService.saveRole(roleToSave);

        assertEquals(savedRole, resultRole);
        verify(roleRepository, times(1)).save(roleToSave);
    }

    @Test
    public void testDeleteRoleById() {
        Long roleIdToDelete = 1L;

        roleService.deleteRoleById(roleIdToDelete);

        verify(roleRepository, times(1)).deleteById(roleIdToDelete);
    }
}
