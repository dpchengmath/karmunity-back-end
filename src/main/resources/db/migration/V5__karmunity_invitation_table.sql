CREATE TABLE karmunity_invitation (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    recipient_id BIGINT NOT NULL,
    karmunity_id BIGINT NOT NULL,
    sent_at TIMESTAMP NOT NULL,
    status VARCHAR(255) NOT NULL,  -- PENDING, ACCEPTED, REJECTED
    FOREIGN KEY (sender_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (recipient_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (karmunity_id) REFERENCES karmunity(id) ON DELETE CASCADE
);