CREATE TABLE users
(
    id BIGINT AUTO_INCREMENT not null PRIMARY KEY,
    firstName VARCHAR(15),
    lastName  VARCHAR(15),
    document  VARCHAR(14) UNIQUE,
    email     VARCHAR(50) UNIQUE,
    password  VARCHAR(20),
    balance   DECIMAL(19, 2),
    userType  ENUM('COMMON', 'MERCHANT')
);

CREATE TABLE transactions
(
    id BIGINT AUTO_INCREMENT not null PRIMARY KEY,
    amount DECIMAL(19, 2),
    sender_id BIGINT,
    receiver_id BIGINT,
    timestamp TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);