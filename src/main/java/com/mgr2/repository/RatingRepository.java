package com.mgr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Rating;

@Repository("ratingRepository")
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	@Query("SELECT r.rate FROM Rating r WHERE r.userid=:userid and r.trainingid=:trainingid")
	Integer findRateByUserIdAndTrainingID(@Param("userid") int user_id, @Param("trainingid") int training_id);
	Rating findByTrainingidAndUserid(int trainingid, int userid);

}
