INSERT INTO boards(title,description,created_by) VALUES('TN State board','Tamilnadu State Board','tom');

INSERT INTO grades(title,description,created_by) VALUES ('12 Standard','12 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('11 Standard','11 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('10 Standard','10 Standard ','tom');
INSERT INTO grades(title,description,created_by) VALUES ('9 Standard','9 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('8 Standard','8 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('7 Standard','7 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('6 Standard','6 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('5 Standard','5 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('4 Standard','4 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('3 Standard','3 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('2 Standard','2 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('1 Standard','1 Standard','tom');

INSERT INTO grades(title,description,created_by) VALUES ('Frontend Developer','Frontend Developer','tom');


INSERT INTO subjects(title,description,created_by) VALUES ('Tamil', 'Tamil','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('English', 'English','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Hindi', 'Hindi','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physics', 'Physics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Chemistry', 'Chemistry','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Maths', 'Maths','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Biology', 'Biology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Computer science', 'Computer science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Accountancy', 'Accountancy','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Economics', 'Economics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Business studies', 'Business studies','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Psychology', 'Psychology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Biotechnology', 'Biotechnology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Home Science', 'Home Science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Fine Arts', 'Fine Arts','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physical Education', 'Physical Education','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Engineering Drawing', 'Engineering Drawing','tom');

INSERT INTO subjects(title,description,created_by) VALUES ('HTML', 'HTML','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('CSS', 'CSS','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('JS', 'JS','tom');

INSERT INTO boards_grades(board_id,grade_id) VALUES (1,1);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,2);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,3);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,4);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,5);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,6);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,7);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,8);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,9);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,10);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,11);
INSERT INTO boards_grades(board_id,grade_id) VALUES (1,12);


INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,4);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,6);


INSERT INTO boards(title,description,created_by) VALUES('TECH@PARK','TECH@PARK','tom');

INSERT INTO boards_grades(board_id,grade_id) VALUES (2,13);

INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,13,18);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,13,19);