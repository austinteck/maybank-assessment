package com.maybank.maybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.maybank.beans.Employee;

/**
 * 
 * @author Austin Teck
 * Employee DAO
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
