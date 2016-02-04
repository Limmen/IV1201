INSERT INTO person (person_id, name, surname, email, username, password)
VALUES (1, 'Greta', 'Borg','dummy_email@.com', 'borg', '21c0cb53a963b6c31ecc8874b852d63dee062e44567fed5fb811f4e947f06bf2');
INSERT INTO person (person_id, name, surname, ssn, email,username,password)
VALUES (2, 'Per', 'Strand', '19671212-1211', 'per@strand.kth.se','strand','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08');

INSERT INTO person (person_id, name, surname, ssn, email,username,password)
VALUES (3, 'admin', 'admin', '00000000-0000', 'admin@admin.com','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

INSERT INTO user_role (user_role, username) VALUES ('recruit','borg');
INSERT INTO user_role (user_role, username) VALUES ('applicant','strand');
INSERT INTO user_role (user_role, username) VALUES ('recruit','admin');

INSERT INTO availability (availability_id,from_date, to_date)
VALUES (1,'2014-02-23', '2014-05-25');
INSERT INTO availability (availability_id,from_date, to_date)
VALUES (2,'2014-07-10', '2014-08-10');

INSERT INTO competence (competence_id, name)
VALUES (1, 'Korvgrillning');
INSERT INTO competence (competence_id, name)
VALUES (2, 'Karuselldrift');

INSERT INTO competence_profile (competence_profile_id,
competence_id, years_of_experience)
VALUES (1,1, 3.5);
INSERT INTO competence_profile (competence_profile_id,
competence_id, years_of_experience)
VALUES (2,2, 2.0);

INSERT INTO application (person_id, availability_id, competence_profile_id)
VALUES(1,1,1);

INSERT INTO application (person_id, availability_id, competence_profile_id)
VALUES(2,2,2);
