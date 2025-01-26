CREATE TABLE member_karmunities (
    member_id BIGINT NOT NULL,
    karmunity_id BIGINT NOT NULL,
    PRIMARY KEY (member_id, karmunity_id),
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
    FOREIGN KEY (karmunity_id) REFERENCES karmunity(id) ON DELETE CASCADE
);