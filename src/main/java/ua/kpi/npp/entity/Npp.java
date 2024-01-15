package ua.kpi.npp.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "npp")
public class Npp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "npp_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "npp")
    private Set<Employee> employees = new LinkedHashSet<>();

    @OneToMany(mappedBy = "npp")
    private Set<User> users = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public Npp(Set<Employee> employees, Role role) {
        this.employees = employees;
        this.role = role;
    }

    public Npp(Employee employee, Role role) {
        this.addEmployee(employee);
        this.role = role;
    }

    public Npp() {
    }

    public Npp(User user) {
        this.addUser(user);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void updateEmployee(Employee employee) {
        for (Employee element : employees) {
            if (element.getId().equals(employee.getId())) {
                employees.remove(element);
                break;
            }
            employees.add(employee);
        }
    }

    public void assignDepartment(Employee employee, Department department) {
        employee.setDepartment(department);
    }

    public List<Employee> getEmployeeListByDepartment(Department department) {
        List<Employee> employeesByDepartment = new ArrayList<>();
        for (Employee element : employees) {
            if(department.equals(element.getDepartment())) {
                employeesByDepartment.add(element);
            }
        }
        return employeesByDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Npp npp)) return false;
        return Objects.equals(id, npp.id) && Objects.equals(employees, npp.employees) && Objects.equals(users, npp.users) && Objects.equals(role, npp.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employees, users, role);
    }
}