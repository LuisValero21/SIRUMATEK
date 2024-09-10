package com.example.sirumatek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sirumatek.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
