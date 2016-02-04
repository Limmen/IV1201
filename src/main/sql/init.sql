CREATE TABLE person (
person_id BIGINT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
surname VARCHAR(255) NOT NULL,
ssn VARCHAR(255),
email VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE competence (
competence_id BIGINT PRIMARY KEY,
name VARCHAR(255) NOT NULL
);
CREATE TABLE user_role (
role_id SERIAL PRIMARY KEY,
user_role VARCHAR(255) NOT NULL,
username VARCHAR(255) NOT NULL REFERENCES person(username)
);
CREATE TABLE availability (
availability_id BIGINT PRIMARY KEY,
from_date DATE NOT NULL,
to_date DATE NOT NULL
);
CREATE TABLE competence_profile (
competence_profile_id BIGINT PRIMARY KEY,
competence_id BIGINT NOT NULL REFERENCES competence(competence_id),
years_of_experience NUMERIC(4,2) NOT NULL
);
CREATE TABLE application(
application_id SERIAL PRIMARY KEY,
person_id BIGINT NOT NULL REFERENCES person(person_id),
availability_id BIGINT NOT NULL REFERENCES availability(availability_id),
competence_profile_id BIGINT NOT NULL REFERENCES competence_profile(competence_profile_id)
)
