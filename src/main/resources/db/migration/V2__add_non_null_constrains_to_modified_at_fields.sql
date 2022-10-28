UPDATE platforms
SET modified_at=now()
where modified_at IS NULL;
ALTER TABLE platforms
    ALTER COLUMN modified_at SET NOT NULL;

UPDATE games
SET modified_at=now()
where modified_at IS NULL;
ALTER TABLE games
    ALTER COLUMN modified_at SET NOT NULL;