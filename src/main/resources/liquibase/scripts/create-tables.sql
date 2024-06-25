CREATE TABLE IF NOT EXISTS course
(
    id BIGSERIAL PRIMARY KEY,
    course_name VARCHAR(60) NOT NULL,
    max_students INTEGER NOT NULL,
    current_students INTEGER NOT NULL,
    course_fee FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS student
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    user_role VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS course_student
(
    course_id BIGINT,
    student_id BIGINT
);

CREATE TABLE IF NOT EXISTS payment
(
    id BIGSERIAL PRIMARY KEY,
    amount FLOAT NOT NULL,
    transaction_id VARCHAR(100) NOT NULL,
    status VARCHAR(30) NOT NULL,
    student_id BIGSERIAL NOT NULL REFERENCES student(id)
    );
