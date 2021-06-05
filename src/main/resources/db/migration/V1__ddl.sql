DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS practices;

CREATE TABLE practices (
  id INT auto_increment PRIMARY KEY,
  name VARCHAR(55) NOT NULL,
  description TEXT NOT NULL,
  type VARCHAR(55) NOT NULL,
  owner VARCHAR(55) NOT NULL,
  meta_data TEXT
);

CREATE TABLE questions (
  id INT auto_increment,
  exam_id int NOT NULL,
  question VARCHAR(500) NOT NULL,
  type VARCHAR(55) NOT NULL,
  answer VARCHAR(500) NOT NULL,
  PRIMARY KEY (id, exam_id),
  FOREIGN KEY (exam_id) REFERENCES practices (id)
);
CREATE TABLE answers (
  id INT auto_increment PRIMARY KEY,
  exam_id int NOT NULL,
  question_id int NOT NULL,
  student_answer VARCHAR(500) NOT NULL,
  FOREIGN KEY (exam_id, question_id) REFERENCES questions (exam_id, id)
);

CREATE TABLE user_notes (
   id INT auto_increment PRIMARY KEY,
   on_type VARCHAR NOT NULL,
   on_instance VARCHAR NOT NULL,
   on_section VARCHAR NOT NULL,
   text VARCHAR(500) NOT NULL,
   notes VARCHAR(500) NOT NULL
);