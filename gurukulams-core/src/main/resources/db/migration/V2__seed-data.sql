-- Boards
INSERT INTO boards(title,description,created_by) VALUES('TN State board','Tamilnadu State Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('KL State board','Kerala State Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('AP State board','Kerala State Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('TS State board','Kerala State Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('Kn State board','Kerala State Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('CBSE board','Kerala State Board','tom');
INSERT INTO boards(title,description,created_by) VALUES('TECH@PARK','TECH@PARK','tom');

INSERT INTO BOARDS_LOCALIZED (BOARD_ID ,LOCALE ,title,description) VALUES(1, 'ta', 'தமிழ் பள்ளிக் கல்வி','தமிழ் பள்ளிக் கல்வித்துறை');

-- Grades
INSERT INTO grades(title,description,created_by) VALUES ('1 Standard','1 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (1,'ta', '1ம் வகுப்பு','1ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('2 Standard','2 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (2,'ta', '2ம் வகுப்பு','2ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('3 Standard','3 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (3,'ta', '3ம் வகுப்பு','3ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('4 Standard','4 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('5 Standard','5 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('6 Standard','6 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('7 Standard','7 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('8 Standard','8 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('9 Standard','9 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('10 Standard','10 Standard ','tom');
INSERT INTO grades(title,description,created_by) VALUES ('11 Standard','11 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('12 Standard','12 Standard','tom');
INSERT INTO grades(title,description,created_by) VALUES ('Frontend Developer','Frontend Developer','tom');

-- Subjects
INSERT INTO subjects(title,description,created_by) VALUES ('Tamil', 'Tamil','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('English', 'English','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Hindi', 'Hindi','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Science', 'Science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Social Science', 'Social Science','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Physics', 'Physics','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Chemistry', 'Chemistry','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('Mathematics', 'Mathematics','tom');
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
INSERT INTO subjects(title,description,created_by) VALUES ('Environmental Science', 'Environmental Science','tom');



INSERT INTO subjects(title,description,created_by) VALUES ('HTML', 'HTML','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('CSS', 'CSS','tom');
INSERT INTO subjects(title,description,created_by) VALUES ('JS', 'JS','tom');

-- tamilnadu board and grades
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
--Kerela board and grades
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,1);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,2);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,3);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,4);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,5);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,6);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,7);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,8);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,9);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,10);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,11);
INSERT INTO boards_grades(board_id,grade_id) VALUES (2,12);


-- 1st Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,1,20);


-- 2nd Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,2,20);


-- 3nd Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,3,20);


-- 4th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,4,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,4,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,4,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,4,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,4,4);


-- 5th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,5,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,5,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,5,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,5,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,5,4);


-- 6th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,6,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,6,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,6,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,6,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,6,4);


-- 7th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,7,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,7,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,7,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,7,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,7,4);


-- 8th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,8,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,8,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,8,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,8,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,8,4);


-- 9th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,9,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,9,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,9,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,9,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,9,4);


-- 10th Standard
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,10,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,10,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,10,5);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,10,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,10,4);


-- 11th Standard

INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,3);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,6);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,7);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,11,9);


-- 12th Standard

INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,1);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,2);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,3);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,8);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,6);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,7);
INSERT INTO boards_grades_subjects(board_id,grade_id,subject_id) VALUES (1,12,9);
