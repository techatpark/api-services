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
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (4,'ta', '4ம் வகுப்பு','4ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('5 Standard','5 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (5,'ta', '5ம் வகுப்பு','5ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('6 Standard','6 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (6,'ta', '6ம் வகுப்பு','6ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('7 Standard','7 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (7,'ta', '7ம் வகுப்பு','7ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('8 Standard','8 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (8,'ta', '8ம் வகுப்பு','8ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('9 Standard','9 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (9,'ta', '9ம் வகுப்பு','9ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('10 Standard','10 Standard ','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (10,'ta', '10ம் வகுப்பு','10ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('11 Standard','11 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (11,'ta', '11ம் வகுப்பு','11ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('12 Standard','12 Standard','tom');
INSERT INTO grades_localized(grade_id,locale,title,description) VALUES (12,'ta', '12ம் வகுப்பு','12ம் வகுப்பு');
INSERT INTO grades(title,description,created_by) VALUES ('Frontend Developer','Frontend Developer','tom');

-- Subjects
INSERT INTO subjects(title,description,created_by) VALUES ('Tamil', 'Tamil','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (1,'ta', 'தமிழ்', 'தமிழ்');
INSERT INTO subjects(title,description,created_by) VALUES ('English', 'English','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (2,'ta', 'ஆங்கிலம்','ஆங்கிலம்');
INSERT INTO subjects(title,description,created_by) VALUES ('Hindi', 'Hindi','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (3,'ta', 'ஹிந்தி ','ஹிந்தி');
INSERT INTO subjects(title,description,created_by) VALUES ('Science', 'Science','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (4,'ta', 'அறிவியல்','அறிவியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Social Science', 'Social Science','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (5,'ta', 'சமூகஅறிவியல்','சமூகஅறிவியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Physics', 'Physics','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (6,'ta', 'இயற்பியல்','இயற்பியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Chemistry', 'Chemistry','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (7,'ta', 'வேதியியல் ','வேதியியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Mathematics', 'Mathematics','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (8,'ta', 'கணிதம் ','கணிதம்');
INSERT INTO subjects(title,description,created_by) VALUES ('Biology', 'Biology','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (9,'ta', 'உயிரியல் ','உயிரியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Computer science', 'Computer science','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (10,'ta', 'கணினி அறிவியல்','கணினி அறிவியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Accountancy', 'Accountancy','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (11,'ta', 'கணக்கியல்','கணக்கியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Economics', 'Economics','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (12,'ta', 'பொருளாதாரம்','பொருளாதாரம்');
INSERT INTO subjects(title,description,created_by) VALUES ('Business studies', 'Business studies','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (13,'ta','வணிக ஆய்வுகள்','வணிக ஆய்வுகள்');
INSERT INTO subjects(title,description,created_by) VALUES ('Psychology', 'Psychology','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (14,'ta','உளவியல்','உளவியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Biotechnology', 'Biotechnology','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (15,'ta','உயிரி தொழில்நுட்பவியல்','உயிரி தொழில்நுட்பவியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Home Science', 'Home Science','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (16,'ta','வீட்டு அறிவியல் ','வீட்டு அறிவியல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Fine Arts', 'Fine Arts','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (17,'ta','நுண்கலைகள்','நுண்கலைகள்');
INSERT INTO subjects(title,description,created_by) VALUES ('Physical Education', 'Physical Education','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (18,'ta','உடற்கல்வி','உடற்கல்வி');
INSERT INTO subjects(title,description,created_by) VALUES ('Engineering Drawing', 'Engineering Drawing','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (19,'ta','பொறியியல் வரைதல்','பொறியியல் வரைதல்');
INSERT INTO subjects(title,description,created_by) VALUES ('Environmental Science', 'Environmental Science','tom');
INSERT INTO subjects_localized(subject_id,locale,title,description) VALUES (20,'ta','சுற்றுச்சூழல் அறிவியல்','சுற்றுச்சூழல் அறிவியல்');



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
