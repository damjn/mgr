CREATE TRIGGER rating_AFTER_UPDATE AFTER UPDATE ON rating FOR EACH ROW
BEGIN
	UPDATE training SET average_rate=(SELECT avg(rate) FROM rating WHERE trainingid=NEW.trainingid) WHERE id=NEW.trainingid;
END