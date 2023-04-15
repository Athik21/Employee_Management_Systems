package com.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.exception.ResourseNotFound;
import com.react.model.Employee;
import com.react.repository.EmployeeRepo;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepo repo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return repo.findAll();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return repo.save(emp);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Long id) {
		Employee emp = repo.findById(id)
				.orElseThrow(() -> new ResourseNotFound("Employee not found"));
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee emps){
		Employee emp = repo.findById(id).orElseThrow(() -> new ResourseNotFound("Employee not found"));
		
		emp.setFirstname(emps.getFirstname());
		emp.setLastname(emps.getLastname());
		emp.setEmailid(emps.getEmailid());
		
		Employee em = repo.save(emp);
		return ResponseEntity.ok(em);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable long id) {
		if(!repo.existsById(id)) {
			throw new ResourseNotFound("Employee not found");
		}
		repo.deleteById(id);
	}
}
