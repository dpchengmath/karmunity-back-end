CREATE TABLE karma_stats (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    member_id BIGINT UNIQUE NOT NULL,
    total_karma INTEGER DEFAULT 0 NOT NULL,
    ACCOUNTABILITY INT DEFAULT 0,
    TEAMWORK INT DEFAULT 0,
    SERVICE INT DEFAULT 0,
    ENCOURAGEMENT INT DEFAULT 0,
    INSPIRATION INT DEFAULT 0,
    INITIATIVE INT DEFAULT 0,
    PATIENCE INT DEFAULT 0,
    RELIABILITY INT DEFAULT 0,
    AUTHENTICITY INT DEFAULT 0,
    KNOWLEDGE INT DEFAULT 0,
    THOUGHTFULNESS INT DEFAULT 0,
    GENEROSITY INT DEFAULT 0,
    PRODUCTIVITY INT DEFAULT 0,
    QUALITY_TIME INT DEFAULT 0,
    OTHER INT DEFAULT 0,
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);