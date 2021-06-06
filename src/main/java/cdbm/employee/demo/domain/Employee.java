package cdbm.employee.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Position position;
	@ManyToOne
	private Person person;
	private BigDecimal salary;

	//----------------------------------------------------------------------------------
	// Implemented from Object class
	//----------------------------------------------------------------------------------
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return position.equals(employee.position) &&
				person.equals(employee.person);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, person);
	}
}
