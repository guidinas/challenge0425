CREATE TABLE IF NOT EXISTS device (
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    brand varchar(255),
    state varchar(255),
    created_at timestamp NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
    );