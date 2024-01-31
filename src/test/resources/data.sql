INSERT INTO progresses (name)
VALUES ('DONE'),
       ('REFACTORING'),
       ('TESTING'),
       ('STUCK'),
       ('FEEDBACK_NEEDED'),
       ('BUSY'),
       ('NOT_STARTED');


INSERT INTO users (dtype, email, display_name)
values ('Student', 'balder@lms.com', 'Balder'),
       ('Student', 'dylan@lms.com', 'Dylan'),
       ('Student', 'karel@lms.com', 'Karel'),
       ('Student', 'jussi@lms.com', 'Jussi'),
       ('Student', 'thomas@lms.com', 'Thomas'),
       ('Student', 'manon@lms.com', 'Manon'),
       ('Student', 'sandro@lms.com', 'Sandro'),
       ('Student', 'wietse@lms.com', 'Wietse'),
       ('Coach', 'rudy@lms.com', 'Rudy'),
       ('Coach', 'christoph@lms.com', 'Christoph'),
       ('Student', 'student@lms.com', 'Student'),
       ('Coach', 'coach@lms.com', 'Coach');

INSERT INTO courses(name)
values
    ('Java'),
    ('Angular'),
    ('.NET');

INSERT INTO class_groups(name, course_id)
values
    ('Java 2023-10', 1),
    ('.NET 2023-10', 3);

INSERT INTO modules(dtype, name, parent_module_id)
values
    ('Module', 'Java basics', null),
    ('Module', '.NET basics', null),
    ('SubModule', 'Loops', 1);

INSERT INTO courses_modules(course_id, module_id)
values
    (1, 1),
    (3, 2);

INSERT INTO class_groups_students(class_group_id, student_id)
values
    (1, 1),
    (1, 2);

INSERT INTO codelabs (name, module_id)
values
    ('codelab01', 1),
    ('codelab02', 1);

INSERT INTO codelab_progresses (codelab_id, progress_id, student_id)
values
    (1,1,1),
    (2,3,1);
