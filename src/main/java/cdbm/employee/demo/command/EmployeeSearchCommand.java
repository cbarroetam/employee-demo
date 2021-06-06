package cdbm.employee.demo.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeSearchCommand implements Serializable {
	private Long positionId;
	private String personName;

	public boolean isValid() {
		return positionId != null || personName != null;
	}

}
