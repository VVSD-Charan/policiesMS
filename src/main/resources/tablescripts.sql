 drop database if exists policies;
 create database policies;
 use policies
 
CREATE TABLE policies (
    policy_id INT AUTO_INCREMENT NOT NULL,
    policy_name VARCHAR(30) NOT NULL,
    policy_price INT NOT NULL,
    primary key (policy_id)
);
