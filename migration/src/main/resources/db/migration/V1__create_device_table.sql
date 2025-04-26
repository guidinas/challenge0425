CREATE TABLE IF NOT EXISTS device (
    id uuid NOT NULL,
    device_name varchar(255) NOT NULL,
    brand varchar(255),
    created_at timestamp NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
    );