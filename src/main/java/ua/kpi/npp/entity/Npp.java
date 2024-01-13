package ua.kpi.npp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
}