CREATE TABLE platforms
(
    id          BIGSERIAL PRIMARY KEY,
    code        TEXT      NOT NULL UNIQUE,
    name        TEXT      NOT NULL UNIQUE,
    released_at TIMESTAMP NOT NULL,
    created_at  TIMESTAMP NOT NULL default now(),
    modified_at TIMESTAMP
);

CREATE TABLE games
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT      NOT NULL,
    released_at TIMESTAMP NOT NULL,
    platform_id BIGSERIAL NOT NULL,
    created_at  TIMESTAMP NOT NULL default now(),
    modified_at TIMESTAMP,
    CONSTRAINT games_platforms_fk FOREIGN KEY (platform_id) REFERENCES platforms (id)
);

CREATE INDEX games_platform_id_idx ON games (platform_id);