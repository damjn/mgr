SELECT EXTRACT(day_minute FROM '2020-06-02 16:10:03');
SET GLOBAL event_scheduler = ON;

show processlist;

show events;

CREATE EVENT log24h
ON SCHEDULE
EVERY 21630 day_minute
DO
UPDATE user_task SET done = 0 where task_id = 1;