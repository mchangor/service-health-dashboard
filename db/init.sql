CREATE TABLE dev.services 
(
    id INTEGER AUTO_INCREMENT, 
    name TEXT NOT NULL, 
    url TEXT NOT NULL,
    creation_time DATETIME DEFAULT NOW(),
    status TEXT,
    PRIMARY KEY (id)
);

INSERT INTO dev.services (id, name, url, status)
VALUES (1, 'test_service1', 'http://localhost:8081/', 'UNKNOWN');