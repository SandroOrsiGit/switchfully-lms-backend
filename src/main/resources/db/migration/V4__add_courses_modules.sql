CREATE TABLE courses_modules
(
    course_id BIGINT NOT NULL,
    module_id BIGINT NOT NULL
);

ALTER TABLE courses_modules
    ADD CONSTRAINT fk_coumod_on_course FOREIGN KEY (course_id) REFERENCES courses (id);

ALTER TABLE courses_modules
    ADD CONSTRAINT fk_coumod_on_module FOREIGN KEY (module_id) REFERENCES modules (id);