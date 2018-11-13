CREATE DATABASE IF NOT EXISTS todos;
USE todos;
create table IF NOT EXISTS todos(
   id INT NOT NULL AUTO_INCREMENT,
   content VARCHAR(100) NOT NULL,
   PRIMARY KEY ( id )
);

INSERT INTO todos (content) VALUES ("Do the shopping");
INSERT INTO todos (content) VALUES ("Wash the car");
INSERT INTO todos (content) VALUES ("Walk the dog");