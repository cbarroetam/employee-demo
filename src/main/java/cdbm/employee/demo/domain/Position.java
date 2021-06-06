package cdbm.employee.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class Position implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	public Position() {
	}

	public Position(Long id) {
		this.id = id;
	}

	public Position(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	//----------------------------------------------------------------------------------
	// Implemented from Object class
	//----------------------------------------------------------------------------------

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Position position = (Position) o;
		return Objects.equals(id, position.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
