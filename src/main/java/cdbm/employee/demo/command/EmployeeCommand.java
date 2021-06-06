package cdbm.employee.demo.command;

import cdbm.employee.demo.domain.Person;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EmployeeCommand implements Serializable {
	private Person person;
	private Long position;
	private BigDecimal salary;
	private Long id;
}
