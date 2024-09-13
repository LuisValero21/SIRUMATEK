package com.example.sirumatek.repository;

import com.example.sirumatek.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value = "SELECT * FROM departamento", nativeQuery = true)
    List<Department> findAllDepartments();
}