INSERT INTO boards(title,description,created_by) VALUES('CBSE','Central Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('TN State board','Tamilnadu State Board','tom');


INSERT INTO grades(title,description,created_by) VALUES ('12 Standard','12 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('11 Standard','11 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('10 Standard','10 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('9 Standard','9 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('8 Standard','8 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('7 Standard','7 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('6 Standard','6 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('5 Standard','5 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('4 Standard','4 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('3 Standard','3 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('2 Standard','2 Standard CBSE','tom');
INSERT INTO grades(title,description,created_by) VALUES ('1 Standard','1 Standard CBSE','tom');

INSERT INTO subjects(title,description,created_by) VALUES ('English', 'English','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Hindi', '12 Standard CBSE Hindi','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Tamil', '12 Standard CBSE Tamil','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physics', '12 Standard CBSE Physics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Chemistry', '12 Standard CBSE Chemistry','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Maths', '12 Standard CBSE Maths','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Biology', '12 Standard CBSE Biology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Computer science', '12 Standard CBSE Computer science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Accountancy', '12 Standard CBSE Accountancy','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Economics', '12 Standard CBSE Economics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Business studies', '12 Standard CBSE Business studies','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Psychology', '12 Standard CBSE Psychology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Biotechnology', '12 Standard CBSE Biotechnology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Home Science', '12 Standard CBSE Home Science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Fine Arts', '12 Standard CBSE Fine Arts','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physical Education', '12 Standard CBSE Physical Education','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Engineering Drawing', '12 Standard CBSE Engineering Drawing','tom');

INSERT INTO subjects(title,description,created_by) VALUES ('English', '11 Standard CBSE English','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Hindi', '11 Standard CBSE Hindi','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Tamil', '11 Standard CBSE Tamil','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physics', '11 Standard CBSE Physics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Chemistry', '11 Standard CBSE Chemistry','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Maths', '11 Standard CBSE Maths','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Biology', '11 Standard CBSE Biology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Computer science', '11 Standard CBSE Computer science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Accountancy', '11 Standard CBSE Accountancy','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Economics', '11 Standard CBSE Economics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Business studies', '11 Standard CBSE Business studies','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Psychology', '11 Standard CBSE Psychology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Biotechnology', '11 Standard CBSE Biotechnology','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Home Science', '11 Standard CBSE Home Science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Fine Arts', '11 Standard CBSE Fine Arts','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physical Education', '11 Standard CBSE Physical Education','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Engineering Drawing', '11 Standard CBSE Engineering Drawing','tom');

INSERT INTO subjects(title,description,created_by) VALUES ('English', '10 Standard CBSE English','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Hindi','10 Standard CBSE Hindi','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Tamil','10 Standard CBSE Tamil','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Maths', '10 Standard CBSE Maths','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Science','10 Standard CBSE Science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Social Science','10 Standard CBSE Social Science','tom');


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

INSERT INTO boards_grades(board_id,grade_id) VALUES (2,1);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,2);

INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,3);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,4);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,5);


INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,1,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,1,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,1,3);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,1,4);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,1,5);


INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,2,6);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (2,2,7);

--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,6);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,7);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,8);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,9);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,10);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,11);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,12);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,13);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,14);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,15);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,16);
--INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,17);

INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,3);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,4);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,6);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,7);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,9);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,10);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,11);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,12);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,13);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,14);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,15);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,16);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,17);

INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,3);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,4);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,6);







