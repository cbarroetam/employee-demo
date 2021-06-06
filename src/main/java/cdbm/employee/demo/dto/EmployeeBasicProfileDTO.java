package cdbm.employee.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeBasicProfileDTO implements Serializable {
	private Long employeeId;
	private String position;
	private String fullName;

	public EmployeeBasicProfileDTO(Long id, String position, String fullName) {
		this.employeeId = id;
		this.position = position;
		this.fullName = fullName;
	}
}
