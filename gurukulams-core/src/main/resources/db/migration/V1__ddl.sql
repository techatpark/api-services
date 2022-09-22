DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS practices;

CREATE TABLE practices (
  id INT auto_increment PRIMARY KEY,
  title VARCHAR(55) NOT NULL,
  description TEXT,
  type VARCHAR(55) NOT NULL,
  meta_data TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(55) NOT NULL,
  modified_at TIMESTAMP,
  modified_by VARCHAR(200)
);

CREATE TABLE practices_localized (
    practice_id INT,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (practice_id) REFERENCES practices (id),
    PRIMARY KEY(practice_id, locale)
);

CREATE TABLE questions (
  id INT auto_increment PRIMARY KEY,
  exam_id INT NOT NULL,
  question TEXT NOT NULL,
  chapter_path VARCHAR(500),
  type VARCHAR(55) NOT NULL,
  answer VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(55) NOT NULL,
  modified_at TIMESTAMP,
  modified_by VARCHAR(200),
  FOREIGN KEY (exam_id) REFERENCES practices (id)
);

CREATE TABLE answers (
  id INT auto_increment PRIMARY KEY,
  exam_id INT NOT NULL,
  question_id INT NOT NULL,
  student_answer VARCHAR(500) NOT NULL,
  FOREIGN KEY (question_id) REFERENCES questions (id)
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
    question_id INT NOT NULL,
    c_value VARCHAR NOT NULL,
    is_answer BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE boards (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT boards_title_constraint UNIQUE (title)
);

CREATE TABLE boards_localized (
    board_id INT,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (board_id) REFERENCES boards (id),
    PRIMARY KEY(board_id, locale)
);

CREATE TABLE grades (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT grades_title_constraint UNIQUE (title)
);

CREATE TABLE grades_localized (
    grade_id INT,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (grade_id) REFERENCES grades (id),
    PRIMARY KEY(grade_id, locale)
);

CREATE TABLE syllabus (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT syllabus_title_constraint UNIQUE (title)
);

CREATE TABLE syllabus_localized (
    syllabus_id INT,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (syllabus_id) REFERENCES syllabus (id),
    PRIMARY KEY(syllabus_id, locale)
);

CREATE TABLE subjects (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT subjects_title_constraint UNIQUE (title)
);

CREATE TABLE subjects_localized (
    subject_id INT,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (subject_id) REFERENCES subjects (id),
    PRIMARY KEY(subject_id, locale)
);

CREATE TABLE books (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    path VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT books_path_constraint UNIQUE (path),
    CONSTRAINT books_title_constraint UNIQUE (title)
);

CREATE TABLE books_localized (
    book_id INT,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (book_id) REFERENCES books (id),
    PRIMARY KEY(book_id, locale)
);

CREATE TABLE institutes (
    id INT auto_increment PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT institutes_title_constraint UNIQUE (title)
);

CREATE TABLE learner (
    id INT auto_increment PRIMARY KEY,
    email VARCHAR(200) NOT NULL,
    image_url VARCHAR(200) NOT NULL,
    provider VARCHAR(50) DEFAULT 'local' NOT NULL,
    password VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT learner_email_constraint UNIQUE (email)
);

CREATE TABLE boards_grades(
    board_id INT NOT NULL,
    grade_id INT NOT NULL,
    PRIMARY KEY(board_id, grade_id),
    FOREIGN KEY (board_id) REFERENCES boards (id),
    FOREIGN KEY (grade_id) REFERENCES grades (id)
);

CREATE TABLE boards_grades_subjects(
    board_id INT NOT NULL,
    grade_id INT NOT NULL,
    subject_id INT NOT NULL,
    PRIMARY KEY(board_id, grade_id, subject_id),
    FOREIGN KEY (board_id) REFERENCES boards (id),
    FOREIGN KEY (grade_id) REFERENCES grades (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
);