DROP TABLE IF EXISTS employee;
CREATE TABLE employee(
  id serial PRIMARY KEY,
  name VARCHAR (50) UNIQUE NOT NULL,
  salary INTEGER NOT NULL,
  city VARCHAR (355) NOT NULL
);

INSERT INTO employee(name, salary, city)
VALUES('Sathish', 40000, 'Madurai');
INSERT INTO employee(name, salary, city)
VALUES('Haripriya', 40000, 'Madurai');
INSERT INTO employee(name, salary, city)
VALUES('Raja', 40000, 'Trichy');