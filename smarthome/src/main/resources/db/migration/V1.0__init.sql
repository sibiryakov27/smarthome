CREATE TABLE sensor (
    sensor_id SERIAL PRIMARY KEY,
    name VARCHAR(30),
    frequency INTEGER,
    location VARCHAR(30),
    sensor_type VARCHAR(15),
    action VARCHAR(15),
    comment TEXT
);
