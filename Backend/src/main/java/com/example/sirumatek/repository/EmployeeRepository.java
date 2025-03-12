package com.example.sirumatek.repository;

import com.example.sirumatek.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM empleado", nativeQuery = true)
    List<Employee> findAllEmployees();
    void deleteById(Long id);

    @Query("SELECT e FROM Employee e WHERE MONTH(e.fechaNacimiento) = :mes AND DAY(e.fechaNacimiento) = :dia")
    List<Employee> findByMonthAndDay(@Param("mes") int mes, @Param("dia") int dia);
}
