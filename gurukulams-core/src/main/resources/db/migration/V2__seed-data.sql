
INSERT INTO boards(title,description,created_by) VALUES('CBSE','Central Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('State','International General Certificate of Secondary Education','tom');

INSERT INTO grades(title,description,created_by) VALUES ('11 Standard','11 standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('12 Standard','11 standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('9 Standard','9 standard IGCSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('10 Standard','10 standard IGCSE','tom');

INSERT INTO syllabus(title,description,created_by) VALUES ('English','English Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Mathematics','Mathematics Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Physics','Physics Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Chemistry','Chemistry Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Biology','Biology Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Computer Science','Computer Science Standard CBSE','tom');

INSERT INTO syllabus(title,description,created_by) VALUES ('English','English Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Hindi','Hindi Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Physics','Physics Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Chemistry','Chemistry Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Mathematics','Mathematics Standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Biology','Biology Standard CBSE','tom');

INSERT INTO syllabus(title,description,created_by) VALUES ('Tamil','Tamil Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('English','English Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Mathematics','Mathematics Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Hindi','Hindi Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Science','Science Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Social','Social Standard State Board','tom');

INSERT INTO syllabus(title,description,created_by) VALUES ('Tamil','Tamil Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('English','English Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Hindi','Hindi Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Mathematics','Mathematics Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Science','Science Standard State Board','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('Social','Social Standard State Board','tom');

INSERT INTO boards_grades(board_id,grade_id) VALUES ('1','1');
INSERT INTO boards_grades_syllabus(board_id,grade_id,syllabus_id) VALUES ('1','1','1');

