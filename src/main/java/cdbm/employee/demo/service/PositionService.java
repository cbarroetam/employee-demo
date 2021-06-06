package cdbm.employee.demo.service;

import cdbm.employee.demo.domain.Employee;
import cdbm.employee.demo.domain.Position;
import cdbm.employee.demo.dto.EmployeeSalaryDTO;
import cdbm.employee.demo.dto.PositionWithSalariesDTO;
import cdbm.employee.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PositionService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<PositionWithSalariesDTO> findAllWithOrderedSalary() {
		List<Employee> items = employeeRepository.findAllByOrderBySalaryDesc();
		Map<Position, List<Employee>> results = items.stream().collect(Collectors.groupingBy(Employee::getPosition));

		ArrayList<PositionWithSalariesDTO> positionWithSalariesDTOS = new ArrayList<>();
		results.forEach((k, v) -> {
			PositionWithSalariesDTO positionWithSalariesDTO = new PositionWithSalariesDTO();
			positionWithSalariesDTO.setId(k.getId());
			positionWithSalariesDTO.setName(k.getName());

			for (Employee e: v) {
				EmployeeSalaryDTO employeeSalaryDTO = new EmployeeSalaryDTO();
				employeeSalaryDTO.setId(e.getId());
				employeeSalaryDTO.setSalary(e.getSalary());
				employeeSalaryDTO.setPerson(e.getPerson());

				positionWithSalariesDTO.addEmployees(employeeSalaryDTO);
			}

			positionWithSalariesDTOS.add(positionWithSalariesDTO);
		});
		return positionWithSalariesDTOS;
	}
}
