
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
INSERT INTO boards_grades(board_id,grade_id) VALUES ('1','2');

INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','1');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','2');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','3');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','4');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','5');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','6');

INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','7');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','8');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','9');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','10');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','11');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','12');


INSERT INTO boards_grades(board_id,grade_id) VALUES ('2','3');
INSERT INTO boards_grades(board_id,grade_id) VALUES ('2','4');

INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('3','13');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('3','14');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('3','15');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('3','16');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('3','17');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('3','18');

INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('4','19');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('4','20');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('4','21');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('4','22');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('4','23');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('4','24');