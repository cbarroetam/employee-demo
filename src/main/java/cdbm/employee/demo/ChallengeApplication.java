package cdbm.employee.demo;

import cdbm.employee.demo.domain.Person;
import cdbm.employee.demo.domain.Position;
import cdbm.employee.demo.repository.PersonRepository;
import cdbm.employee.demo.repository.PositionRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeApplication {

	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private PersonRepository personRepository;


	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			positionRepository.save(new Position(1L, "QA"));
			positionRepository.save(new Position(2L, "DEV"));
			personRepository.save(new Person(1L, "John", "Doe"));
		};
	}
}

