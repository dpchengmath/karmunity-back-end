CREATE OR REPLACE FUNCTION update_karma_stats()
    RETURNS TRIGGER AS $$
DECLARE
    act text;
BEGIN
    act := lower(NEW.karma_act::text);

    RAISE NOTICE 'Trigger fired for karma_stats_id: %, karma_act: %', NEW.karma_stats_id, act;

    EXECUTE FORMAT(
            'UPDATE karma_stats SET %I = %I + 1, total_karma = total_karma + 1 WHERE id = $1',
            act, act
            ) USING NEW.karma_stats_id;

    RAISE NOTICE 'Update attempted for karma_stats_id: %', NEW.karma_stats_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS karma_entry_trigger ON karma_entry;

CREATE TRIGGER karma_entry_trigger
    AFTER INSERT ON karma_entry
    FOR EACH ROW
EXECUTE FUNCTION update_karma_stats();
