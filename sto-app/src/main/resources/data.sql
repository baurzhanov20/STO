CREATE ROLE myuser WITH LOGIN PASSWORD 'secret';
CREATE TYPE status_enum AS ENUM ('NEW', 'ACCEPTED', 'IN_PROGRESS', 'DONE');

CREATE TABLE IF NOT EXISTS repair_request (
                                              id BIGSERIAL PRIMARY KEY,
                                              client_name VARCHAR(100) NOT NULL,
    client_phone VARCHAR(20),
    car_details TEXT NOT NULL,
    status status_enum NOT NULL DEFAULT 'NEW',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS status_history (
                                              id BIGSERIAL PRIMARY KEY,
                                              request_id BIGINT REFERENCES repair_request(id) ON DELETE CASCADE,
    old_status status_enum,
    new_status status_enum NOT NULL,
    changed_by VARCHAR(50) NOT NULL,
    changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reason TEXT
    );
