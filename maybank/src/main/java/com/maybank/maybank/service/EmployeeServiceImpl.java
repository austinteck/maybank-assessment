package com.maybank.maybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.maybank.maybank.beans.Employee;
import com.maybank.maybank.repository.EmployeeRepository;
import com.maybank.maybank.util.ResourceNotFoundException;

/**
 * 
 * @author Austin Teck
 * Controller should not directly access repository; use service implementation class to make information accessible
 *
 */
@Service
@Component(value = "EmployeeServiceImpl")
public class EmployeeServiceImpl {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 
	 * @return list of all employee records 
	 */
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	/**
	 * 
	 * @param paging - value indicating which page to retrieve from
	 * @return paginated records, specified by page number and page size (in this case, 10)
	 */
	public Page<Employee> getAllEmployees(Pageable paging) {
		return employeeRepository.findAll(paging);
	}
	
	/**
	 * 
	 * @param employeeId - Employee primary key
	 * @return employee record, retrieved via id
	 * @throws ResourceNotFoundException - throw custom exception should employee retrieval fails
	 */
	public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: " + employeeId));
		
		return employee;
	}
	
	/**
	 * 
	 * @param employee - object to be persisted
	 * @return new/updated employee record post-persistence
	 */
	public Employee saveEmployeeRecord(Employee employee) {
		return employeeRepository.save(employee);
	}

}
