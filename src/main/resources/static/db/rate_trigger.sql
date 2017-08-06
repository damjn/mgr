CREATE TRIGGER rating_AFTER_INSERT AFTER INSERT ON rating FOR EACH ROW
BEGIN
	#DECLARE new_training_avg decimal;
	
    #SET new_training_avg := (SELECT avg(rate) FROM rating WHERE trainingid=NEW.trainingid);
	#UPDATE training SET average_rate=new_training_avg WHERE trainingid=NEW.trainingid;
    UPDATE training SET average_rate=(SELECT avg(rate) FROM rating WHERE trainingid=NEW.trainingid) WHERE id=NEW.trainingid;
END