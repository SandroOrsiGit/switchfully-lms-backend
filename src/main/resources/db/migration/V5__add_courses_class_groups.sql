CREATE TABLE courses_class_groups
(
    class_group_id BIGINT NOT NULL,
    course_id      BIGINT NOT NULL
);

ALTER TABLE courses_class_groups
    ADD CONSTRAINT fk_couclagro_on_class_group FOREIGN KEY (class_group_id) REFERENCES class_groups (id);

ALTER TABLE courses_class_groups
    ADD CONSTRAINT fk_couclagro_on_course FOREIGN KEY (course_id) REFERENCES courses (id);