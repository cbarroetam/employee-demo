package cdbm.employee.demo.controller;

import cdbm.employee.demo.command.EmployeeSearchCommand;
import cdbm.employee.demo.command.EmployeeCommand;
import cdbm.employee.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("")
	public ResponseEntity createEmployee(@RequestBody EmployeeCommand employee) {
		try {
			return ResponseEntity.ok(employeeService.save(employee));
		} catch (Throwable th) {
			log.error("An error happened when creating a new Employee", th);
			return ResponseEntity.badRequest().body(th.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteEmployee(@PathVariable("id") Long id) {
		employeeService.delete(id);
		return ResponseEntity.ok("");
	}

	@PutMapping("")
	public ResponseEntity updateEmployee(@RequestBody EmployeeCommand employee) {
		try {
			return ResponseEntity.ok(employeeService.update(employee));
		} catch (Throwable th) {
			log.error("An error happened when updating Employee data", th);
			return ResponseEntity.badRequest().body(th.getMessage());
		}
	}

	@GetMapping("")
	public ResponseEntity findEmployeesByPositionOrName(
			@RequestParam(value="personName", required = false) String personName,
			@RequestParam(value="positionId", required = false) Long positionId) {

		EmployeeSearchCommand employeeSearchCommand = new EmployeeSearchCommand();
		employeeSearchCommand.setPersonName(personName);
		employeeSearchCommand.setPositionId(positionId);

		return ResponseEntity.ok(employeeService.findEmployeesByCriteria(employeeSearchCommand));
	}
}
