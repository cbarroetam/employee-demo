package cdbm.employee.demo.dto;

import cdbm.employee.demo.domain.Person;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EmployeeSalaryDTO implements Serializable {
	private Long id;
	private BigDecimal salary;
	private Person person;
}
