ALTER TABLE platforms
    ALTER COLUMN modified_at SET DEFAULT now();

ALTER TABLE games
    ALTER COLUMN modified_at SET DEFAULT now();