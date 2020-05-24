DROP TABLE IF EXISTS exam_scripts;
CREATE TABLE exam_scripts (
  id SERIAL PRIMARY KEY,
  exam_id int NOT NULL,
  script BYTEA NOT NULL,
  FOREIGN KEY (exam_id) REFERENCES exams (id)
)