package com.mgr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Rating;

@Repository("ratingRepository")
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	@Query("SELECT r.rate FROM Rating r WHERE r.user_id=:user_id and r.training_id=:training_id")
	Integer findRateByUserIdAndTrainingID(@Param("user_id") int user_id, @Param("training_id") int training_id);

}
