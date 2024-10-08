package com.example.sirumatek.repository;

import com.example.sirumatek.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM empleado", nativeQuery = true)
    List<Employee> findAllEmployees();
}
