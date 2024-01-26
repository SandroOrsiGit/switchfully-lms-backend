INSERT INTO progresses (name)
VALUES ('DONE'),
       ('REFACTORING'),
       ('TESTING'),
       ('STUCK'),
       ('FEEDBACK_NEEDED'),
       ('BUSY'),
       ('NOT_STARTED');


INSERT INTO users (dtype, email, display_name)
values ('Student', 'Balder@lms.com', 'Balder'),
       ('Student', 'Dylan@lms.com', 'Dylan'),
       ('Student', 'Karel@lms.com', 'Karel'),
       ('Student', 'Jussi@lms.com', 'Jussi'),
       ('Student', 'Thomas@lms.com', 'Thomas'),
       ('Student', 'Manon@lms.com', 'Manon'),
       ('Student', 'Sandro@lms.com', 'Sandro'),
       ('Student', 'Wietse@lms.com', 'Wietse'),
       ('Coach', 'Rudy@lms.com', 'Rudy'),
       ('Coach', 'Christoph@lms.com', 'Christoph');

INSERT INTO courses(name)
values ('Java'),
       ('Angular'),
       ('.NET');

INSERT INTO class_groups(name, course_id)
values ('Java 2023-10', 1),
       ('.NET 2023-10', 3);

INSERT INTO modules(dtype, name, parent_module_id)
values ('Module', 'Java basics', null),
       ('Module', '.NET basics', null),
       ('SubModule', 'Loops', 1);

INSERT INTO courses_class_groups(course_id, class_group_id)
values (1, 1),
       (3, 2);

INSERT INTO courses_modules(course_id, module_id)
values (1, 1),
       (3, 2);

INSERT INTO class_groups_students(class_group_id, student_id)
values (1, 1),
       (1, 2);



