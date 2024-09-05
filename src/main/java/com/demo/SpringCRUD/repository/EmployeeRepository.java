package com.demo.SpringCRUD.repository;


import com.demo.SpringCRUD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
EmployeeRepository extends JpaRepository <Employee, Long> {
}
