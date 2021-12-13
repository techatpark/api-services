DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS practices;

CREATE TABLE practices (
  id INT auto_increment PRIMARY KEY,
  name VARCHAR(55) NOT NULL,
  description TEXT,
  book VARCHAR(55),
  type VARCHAR(55) NOT NULL,
  meta_data TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(55) NOT NULL,
  modified_at TIMESTAMP,
  modified_by VARCHAR(200),
  CONSTRAINT book_constraint UNIQUE (book)
);

CREATE TABLE questions (
  id INT auto_increment,
  exam_id int NOT NULL,
  question TEXT NOT NULL,
  chapter_path VARCHAR(500),
  type VARCHAR(55) NOT NULL,
  answer VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(55) NOT NULL,
  modified_at TIMESTAMP,
  modified_by VARCHAR(200),
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
   created_by VARCHAR(55) NOT NULL,
   prev_word VARCHAR(500) NOT NULL,
   text VARCHAR(500) NOT NULL,
   note VARCHAR(500)
);

CREATE TABLE question_choices (
    id INT auto_increment PRIMARY KEY,
    question_id int NOT NULL,
    value TEXT NOT NULL,
    is_answer BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE boards (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200)
);

CREATE TABLE grades (
    id INT auto_increment PRIMARY KEY,
    board_id INT,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    FOREIGN KEY (board_id) REFERENCES boards (id)
);

CREATE TABLE syllabus (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200)
);

CREATE TABLE institutes (
    id INT auto_increment PRIMARY KEY,
    board_id INT,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    FOREIGN KEY (board_id) REFERENCES boards (id)
);

CREATE TABLE learner (
    id INT auto_increment PRIMARY KEY,
    name VARCHAR(55),
    email VARCHAR(55),
    displaying TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

