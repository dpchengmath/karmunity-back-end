CREATE TABLE shoutout (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    shoutout_content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES member(id) ON DELETE CASCADE
);