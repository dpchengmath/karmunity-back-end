CREATE TABLE IF NOT EXISTS karma_entry (
    id SERIAL PRIMARY KEY,
    karma_giver_id BIGINT NOT NULL,
    karma_receiver_id BIGINT NOT NULL,
    karma_act karma_act NOT NULL,  -- Corrected to match the type name
    kudos VARCHAR(255) NOT NULL,
    karma INT NOT NULL DEFAULT 1,  -- Always 1
    sent_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    karma_stats_id BIGINT NOT NULL,
    FOREIGN KEY (karma_giver_id) REFERENCES member(id),
    FOREIGN KEY (karma_receiver_id) REFERENCES member(id),
    FOREIGN KEY (karma_stats_id) REFERENCES karma_stats(id)
);
