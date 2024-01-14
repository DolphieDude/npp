package ua.kpi.npp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ua.kpi.npp.entity.Role;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        Role role = new Role();
        role.setName("TEST_ROLE");
        testEntityManager.persist(role);

        Role foundRole = roleRepository.findByName("TEST_ROLE");

        assertEquals("TEST_ROLE", foundRole.getName());
    }

    @Test
    public void testFindByDescriptionContaining() {
        Role role = new Role();
        role.setName("TEST_ROLE");
        role.setDescription("Description containing keyword");
        testEntityManager.persist(role);

        List<Role> roles = roleRepository.findByDescriptionContaining("keyword");

        assertTrue(roles.size() > 0);
        assertEquals("TEST_ROLE", roles.get(0).getName());
    }

    @Test
    public void testFindByOrderByNameAsc() {
        Role role1 = new Role();
        role1.setName("B_ROLE");
        testEntityManager.persist(role1);

        Role role2 = new Role();
        role2.setName("A_ROLE");
        testEntityManager.persist(role2);

        List<Role> roles = roleRepository.findByOrderByNameAsc();

        assertEquals("A_ROLE", roles.get(0).getName());
        assertEquals("B_ROLE", roles.get(1).getName());
    }

    @Test
    public void testSearchRoles() {
        Role role1 = new Role();
        role1.setName("TEST_ROLE_1");
        testEntityManager.persist(role1);

        Role role2 = new Role();
        role2.setName("TEST_ROLE_2");
        testEntityManager.persist(role2);

        List<Role> roles = roleRepository.searchRoles("TEST_ROLE_");

        assertEquals(2, roles.size());
        assertTrue(roles.stream().allMatch(r -> r.getName().startsWith("TEST_ROLE_")));
    }
}
