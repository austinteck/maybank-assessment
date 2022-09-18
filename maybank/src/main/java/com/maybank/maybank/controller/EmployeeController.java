package com.maybank.maybank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maybank.maybank.beans.Employee;
import com.maybank.maybank.beans.JSONServiceDTO;
import com.maybank.maybank.service.EmployeeServiceImpl;
import com.maybank.maybank.util.ResourceNotFoundException;

/**
 * 
 * @author Austin Teck
 * 
 */
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	private Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	private List<JSONServiceDTO> prettyPrintJSONDTO(List<Employee> employees) {
		List<JSONServiceDTO> jsonDTOList = new ArrayList<>();
		for (int i = 0; i < employees.size(); i++) {
			JSONServiceDTO json = new JSONServiceDTO(employees.get(i).getId(), employees.get(i).getName(), employees.get(i).getAge());
			jsonDTOList.add(json);
		}
		
		return jsonDTOList;
	}
	
	/**
	 * @return list of employee objects, retrieved via JPA
	 */
	@GetMapping("/")
	public List<Employee> getAllEmployees(HttpServletRequest request){
		List<Employee> employees = employeeService.getAllEmployees();
		
		logger.info("REQUEST: " + request.getRequestURI());
		logger.info("RESPONSE: " + prettyPrintJSONDTO(employees).toString());
		return employeeService.getAllEmployees();
	}
	
	/**
	 * 
	 * @param pageNumber - value indicating which page to retrieve from
	 * @return paginated records, specified by page number and page size (in this case, 10)
	 */
	@GetMapping("/page/{pageNumber}")
	public List<Employee> getEmployeesByPage(@PathVariable(value = "pageNumber") int pageNumber, HttpServletRequest request){
		Pageable paging = PageRequest.of(pageNumber - 1, 10, Sort.by("id").ascending());
		Page<Employee> page = employeeService.getAllEmployees(paging);
		
		logger.info("REQUEST: " + request.getRequestURI());
		logger.info("RESPONSE: " + prettyPrintJSONDTO(page.getContent()).toString());
		
		return page.getContent();	
	}
	
	/**
	 * 
	 * @param employeeId - Employee primary key
	 * @return employee record, retrieved via id
	 * @throws ResourceNotFoundException - throw custom exception should employee retrieval fails
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId, HttpServletRequest request) throws ResourceNotFoundException{
		Employee employee = employeeService.getEmployeeById(employeeId);
		
		logger.info("REQUEST: " + request.getRequestURI());
		logger.info("RESPONSE: " + employee.toString());
		return ResponseEntity.ok().body(employee);
	}
	
	/**
	 * 
	 * @apiNote system currently allows duplicate records (name, age)
	 * @param employee - request body of which values are supplied by end user
	 * @return new employee record
	 */
	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee employee, HttpServletRequest request) {
		logger.info("REQUEST: " + request.getRequestURI());
		logger.info("RESPONSE: " + employee.toString());
		return employeeService.saveEmployeeRecord(employee);
	}
	
	/**
	 * 
	 * @param employeeId - Employee primary key, used to retrieve existing employee
	 * @param updatedEmployeeDetails - request body containing values to overwrite existing record
	 * @return updated employee record
	 * @throws ResourceNotFoundException - throw custom error should employee retrieval fails
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
	         @RequestBody Employee updatedEmployeeDetails, HttpServletRequest request) throws ResourceNotFoundException {
        Employee employee = employeeService.getEmployeeById(employeeId);
        
        employee.setName(updatedEmployeeDetails.getName());
        employee.setAge(updatedEmployeeDetails.getAge());
        final Employee updatedEmployee = employeeService.saveEmployeeRecord(employee);
        
        logger.info("REQUEST: " + request.getRequestURI());
		logger.info("RESPONSE: " + updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
	}
}
