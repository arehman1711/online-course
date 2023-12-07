 CREATE TABLE "app_user" (
                        id int NOT NULL,
                        email varchar(255) DEFAULT NULL,
                        name varchar(255) DEFAULT NULL,
                        password varchar(255) DEFAULT NULL,
                        role varchar(255) DEFAULT NULL,
                        image varchar(255) DEFAULT NULL,
                        token_expiry_time datetime(6) DEFAULT NULL,
                        verification_token varchar(255) DEFAULT NULL,
                         PRIMARY KEY (id)
);

 CREATE TABLE "course" (
                           id int NOT NULL,
                           COURSE_NAME varchar(255) DEFAULT NULL,
                           course_Description varchar(255) DEFAULT NULL,
                           duration int DEFAULT 0,
                           price int DEFAULT 0,
                           rating varchar(255) DEFAULT NULL,
                           trainer varchar(255) DEFAULT NULL,
                           PRIMARY KEY (id)
 );

