package cdbm.employee.demo.controller;

import cdbm.employee.demo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/position")
public class PositionController {

	@Autowired
	private PositionService positionService;

	@GetMapping("")
	public ResponseEntity findPositionsWithSalaries() {
		return ResponseEntity.ok(positionService.findAllWithOrderedSalary());
	}
 }
