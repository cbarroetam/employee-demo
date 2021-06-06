package cdbm.employee.demo.repository;

import cdbm.employee.demo.domain.Employee;
import cdbm.employee.demo.domain.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findAllByPersonNameOrderById(String personName);

	List<Employee> findAllByPositionOrPersonNameOrderById(Position position, String personName);

	List<Employee> findAllByOrderByIdDesc();

	List<Employee> findAllByOrderBySalaryDesc();
}
