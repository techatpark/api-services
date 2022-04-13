
INSERT INTO boards(title,description,created_by) VALUES('CBSE','Central Board','tom');

INSERT INTO grades(title,description,created_by) VALUES ('10 Standard','10 standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('11 Standard','11 standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('12 Standard','11 standard CBSE','tom');

INSERT INTO syllabus(title,description,created_by) VALUES ('maths','maths standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('physics','physics standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('chemistry','chemistry standard CBSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('biology','biology standard CBSE','tom');

INSERT INTO boards_grades(board_id,grade_id) VALUES ('1','1');
INSERT INTO boards_grades(board_id,grade_id) VALUES ('1','2');
INSERT INTO boards_grades(board_id,grade_id) VALUES ('1','3');

INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','1');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','2');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('1','3');

INSERT INTO boards(title,description,created_by) VALUES('IGCSE','International General Certificate of Secondary Education','tom');

INSERT INTO grades(title,description,created_by) VALUES ('1 Standard','1 standard IGCSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('2 Standard','2 standard IGCSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('3 Standard','3 standard IGCSE','tom');

INSERT INTO syllabus(title,description,created_by) VALUES ('hindi','hindi standard IGCSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('english','english standard IGCSE','tom');
INSERT INTO syllabus(title,description,created_by) VALUES ('sanskrit','sanskrit standard IGCSE','tom');

INSERT INTO boards_grades(board_id,grade_id) VALUES ('2','4');
INSERT INTO boards_grades(board_id,grade_id) VALUES ('2','5');
INSERT INTO boards_grades(board_id,grade_id) VALUES ('2','6');

INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','4');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','5');
INSERT INTO grades_syllabus(grade_id,syllabus_id) VALUES ('2','6');