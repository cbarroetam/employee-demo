package cdbm.employee.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PositionWithSalariesDTO {

	private Long id;
	private String name;
	private List<EmployeeSalaryDTO> employees = new ArrayList<>();

	public void addEmployees(EmployeeSalaryDTO emp) {
		 employees.add(emp);
	}
}
