CREATE TABLE person (
						id INT AUTO_INCREMENT  PRIMARY KEY,
						name VARCHAR(255) NOT NULL,
						last_name VARCHAR(255) NOT NULL,
						address VARCHAR(255) DEFAULT NULL,
						cellphone VARCHAR(255) DEFAULT NULL,
						city_name VARCHAR(255) DEFAULT NULL
);

CREATE TABLE position (
						  id INT AUTO_INCREMENT  PRIMARY KEY,
						  name VARCHAR(255) NOT NULL
);

CREATE TABLE employee (
						  id INT AUTO_INCREMENT  PRIMARY KEY,
						  position_id INT NOT NULL,
						  person_id INT NOT NULL,
						  salary FLOAT NULL
);
ALTER TABLE employee ADD FOREIGN KEY (position_id) REFERENCES position(id);
ALTER TABLE employee ADD FOREIGN KEY (person_id) REFERENCES person(id);
