CREATE TABLE questions (
  id UUID PRIMARY KEY,
  question TEXT NOT NULL,
  explanation TEXT NOT NULL,
  type VARCHAR(55) NOT NULL,
  answer VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(55) NOT NULL,
  modified_at TIMESTAMP,
  modified_by VARCHAR(200)
);

CREATE TABLE questions_localized (
    question_id UUID,
    locale VARCHAR(8) NOT NULL,
    question TEXT NOT NULL,
    explanation TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions (id),
    PRIMARY KEY(question_id, locale)
);

CREATE TABLE question_choices (
    id UUID PRIMARY KEY,
    question_id UUID NOT NULL,
    c_value VARCHAR NOT NULL,
    is_answer BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE question_choices_localized (
    choice_id UUID,
    locale VARCHAR(8) NOT NULL,
    c_value VARCHAR NOT NULL,
    FOREIGN KEY (choice_id) REFERENCES question_choices (id),
    PRIMARY KEY (choice_id, locale)
);

CREATE TABLE answers (
  id UUID PRIMARY KEY,
  exam_id UUID NOT NULL,
  question_id UUID NOT NULL,
  student_answer VARCHAR(500) NOT NULL,
  FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE annotations (
   id UUID PRIMARY KEY,
   on_type VARCHAR NOT NULL,
   on_instance VARCHAR NOT NULL,
   locale VARCHAR(8),
   json_value JSON NOT NULL,
   created_by VARCHAR(55) NOT NULL
);

CREATE TABLE boards (
    id UUID PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT boards_title_constraint UNIQUE (title)
);

CREATE TABLE boards_localized (
    board_id UUID,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (board_id) REFERENCES boards (id),
    PRIMARY KEY(board_id, locale)
);

CREATE TABLE grades (
    id UUID PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT grades_title_constraint UNIQUE (title)
);

CREATE TABLE grades_localized (
    grade_id UUID,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (grade_id) REFERENCES grades (id),
    PRIMARY KEY(grade_id, locale)
);

CREATE TABLE subjects (
    id UUID PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT subjects_title_constraint UNIQUE (title)
);

CREATE TABLE subjects_localized (
    subject_id UUID,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (subject_id) REFERENCES subjects (id),
    PRIMARY KEY(subject_id, locale)
);

CREATE TABLE books (
    id UUID PRIMARY KEY,
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
    book_id UUID,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (book_id) REFERENCES books (id),
    PRIMARY KEY(book_id, locale)
);

CREATE TABLE institutes (
    id UUID PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT institutes_title_constraint UNIQUE (title)
);

CREATE TABLE learner (
    id UUID PRIMARY KEY,
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

CREATE TABLE handle (
    id VARCHAR(55) PRIMARY KEY,
    type VARCHAR(55),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE learner_profile (
    id VARCHAR(55) PRIMARY KEY,
    learner_id UUID NOT NULL UNIQUE,
    first_name VARCHAR(200),
    last_name VARCHAR(200),
    FOREIGN KEY (learner_id) REFERENCES learner (id),
    FOREIGN KEY (id) REFERENCES handle (id)
);

CREATE TABLE boards_grades(
    board_id UUID NOT NULL,
    grade_id UUID NOT NULL,
    PRIMARY KEY(board_id, grade_id),
    FOREIGN KEY (board_id) REFERENCES boards (id),
    FOREIGN KEY (grade_id) REFERENCES grades (id)
);

CREATE TABLE boards_grades_subjects(
    board_id UUID NOT NULL,
    grade_id UUID NOT NULL,
    subject_id UUID NOT NULL,
    PRIMARY KEY(board_id, grade_id, subject_id),
    FOREIGN KEY (board_id) REFERENCES boards (id),
    FOREIGN KEY (grade_id) REFERENCES grades (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

CREATE TABLE boards_grades_subjects_books(
    board_id UUID NOT NULL,
    grade_id UUID NOT NULL,
    subject_id UUID NOT NULL,
    book_id UUID NOT NULL,
    PRIMARY KEY(board_id, grade_id, subject_id),
    FOREIGN KEY (board_id) REFERENCES boards (id),
    FOREIGN KEY (grade_id) REFERENCES grades (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE categories (
    id VARCHAR(55) PRIMARY KEY,
    title TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT categories_title_constraint UNIQUE (title)
);

CREATE TABLE categories_localized (
    category_id VARCHAR(55),
    locale VARCHAR(8) NOT NULL,
    title TEXT,
    FOREIGN KEY (category_id) REFERENCES categories (id),
    PRIMARY KEY(category_id, locale)
);

CREATE TABLE questions_categories (
    question_id UUID NOT NULL,
    category_id VARCHAR(55) NOT NULL,
    PRIMARY KEY(question_id, category_id),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE tags (
    id VARCHAR(55) PRIMARY KEY,
    title TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT tags_title_constraint UNIQUE (title)
);

CREATE TABLE tags_localized (
    tag_id VARCHAR(55),
    locale VARCHAR(8) NOT NULL,
    title TEXT,
    FOREIGN KEY (tag_id) REFERENCES tags (id),
    PRIMARY KEY(tag_id, locale)
);

CREATE TABLE questions_tags (
    question_id UUID NOT NULL,
    tag_id VARCHAR(55) NOT NULL,
    PRIMARY KEY(question_id, tag_id),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE events (
    id UUID PRIMARY KEY,
    title VARCHAR(55),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(55) NOT NULL,
    modified_at TIMESTAMP,
    modified_by VARCHAR(200),
    CONSTRAINT event_title_constraint UNIQUE (title)
    --add date field
);


CREATE TABLE events_localized (
    event_id UUID,
    locale VARCHAR(8) NOT NULL,
    title VARCHAR(55),
    description TEXT,
    FOREIGN KEY (event_id) REFERENCES events (id),
    PRIMARY KEY(event_id, locale)
);

CREATE TABLE event_users (
    event_id UUID,
    user_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (user_id) REFERENCES learner (id),
    PRIMARY KEY(event_id, user_id)
);