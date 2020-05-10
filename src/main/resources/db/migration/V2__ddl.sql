DROP TABLE IF EXISTS script_files;
CREATE TABLE script_files (
  id SERIAL PRIMARY KEY,
  exam_id int NOT NULL,
  script BYTEA NOT NULL,
  FOREIGN KEY (exam_id) REFERENCES exams (id)
)