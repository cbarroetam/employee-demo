package cdbm.employee.demo.service;

import cdbm.employee.demo.command.EmployeeCommand;
import cdbm.employee.demo.command.EmployeeSearchCommand;
import cdbm.employee.demo.domain.Employee;
import cdbm.employee.demo.domain.Person;
import cdbm.employee.demo.domain.Position;
import cdbm.employee.demo.dto.EmployeeBasicProfileDTO;
import cdbm.employee.demo.repository.EmployeeRepository;
import cdbm.employee.demo.repository.PersonRepository;
import cdbm.employee.demo.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PositionRepository positionRepository;

	public EmployeeBasicProfileDTO save(EmployeeCommand employee) {
		Employee newEmployee = new Employee();

		Optional<Position> position = positionRepository.findById(employee.getPosition());
		if (position.isPresent()) {

			if (employee.getPerson() != null) {
				Person newPerson = null;
				Optional<Person> person = null;

				if (employee.getPerson().getId() != null) {
					person = personRepository.findById(employee.getPerson().getId());
				}

				if (isANewPerson(person)) {
					newPerson = buildPersonObject(employee);
					newPerson = personRepository.save(newPerson);
					log.info("Added new Person with ID=" + newPerson.getId());
				} else {
					newPerson = person.get();
				}

				newEmployee = saveEmployee(employee, newEmployee, position, newPerson);

				log.info("Added new Employee with ID=" + newEmployee.getId());
				return new EmployeeBasicProfileDTO(
						newEmployee.getId(),
						newEmployee.getPosition().getName(),
						newEmployee.getPerson().getFullName());
			}
			throw new IllegalArgumentException("Person info must be provided");
		}

		throw new IllegalArgumentException(String.format("Position with id(%s) does not exist", employee.getPosition()));
	}

	public void delete(Long employeeId) {
		employeeRepository.deleteById(employeeId);
		log.info(String.format("Deleted employee with ID=%s", employeeId));
	}

	public EmployeeBasicProfileDTO update(EmployeeCommand employee) {

		Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
		if (employeeOptional.isPresent()) {
			Employee employeeToUpdate = employeeOptional.get();

			Optional<Position> position = positionRepository.findById(employee.getPosition());
			if (position.isPresent()) {

				if (employee.getPerson() != null) {
					Person personToUpdate = updateAndPersistPerson(employee, employeeToUpdate.getPerson());
					log.info(String.format("Updated Person info with ID=%s for Employee ID=%s", employeeToUpdate.getId(), employee.getId()));

					employeeToUpdate.setPerson(personToUpdate);
					employeeToUpdate.setPosition(position.get());
					employeeToUpdate.setSalary(employee.getSalary());

					log.info("Updated Employee with ID=" + employeeToUpdate.getId());
					return new EmployeeBasicProfileDTO(
							employeeToUpdate.getId(),
							employeeToUpdate.getPosition().getName(),
							employeeToUpdate.getPerson().getFullName());
				}
				throw new IllegalArgumentException("Person info must be provided");
			}
		}
		throw new IllegalArgumentException(String.format("Employee with id(%s) does not exist", employee.getId()));
	}

	public List<Employee> findEmployeesByCriteria(EmployeeSearchCommand employeeSearchCommand ) {

		if (!employeeSearchCommand.isValid()) {
			return employeeRepository.findAllByOrderByIdDesc();
		} else {
			if (employeeSearchCommand.getPositionId() != null) {
				return employeeRepository.findAllByPositionOrPersonNameOrderById(
						new Position(employeeSearchCommand.getPositionId()),
						employeeSearchCommand.getPersonName());
			} else {
				return employeeRepository.findAllByPersonNameOrderById(employeeSearchCommand.getPersonName());
			}
		}
	}

	private boolean isANewPerson(Optional<Person> person) {
		return person == null || (person != null && person.isEmpty());
	}

	private Person updateAndPersistPerson(EmployeeCommand employee, Person personToUpdate) {
		personToUpdate.setName(employee.getPerson().getName());
		personToUpdate.setLastName(employee.getPerson().getLastName());
		personToUpdate.setAddress(employee.getPerson().getAddress());
		personToUpdate.setCityName(employee.getPerson().getCityName());
		personToUpdate.setCellphone(employee.getPerson().getCellphone());
		personRepository.save(personToUpdate);
		return personToUpdate;
	}

	private Employee saveEmployee(EmployeeCommand employee, Employee newEmployee, Optional<Position> position, Person newPerson) {
		newEmployee.setPerson(newPerson);
		newEmployee.setPosition(position.get());
		newEmployee.setSalary(employee.getSalary());
		newEmployee = employeeRepository.save(newEmployee);
		return newEmployee;
	}

	private Person buildPersonObject(EmployeeCommand employee) {
		Person newPerson  = new Person();
		newPerson.setName(employee.getPerson().getName());
		newPerson.setLastName(employee.getPerson().getLastName());
		newPerson.setAddress(employee.getPerson().getAddress());
		newPerson.setCellphone(employee.getPerson().getCellphone());
		newPerson.setCityName(employee.getPerson().getCityName());
		return newPerson;
	}
}
