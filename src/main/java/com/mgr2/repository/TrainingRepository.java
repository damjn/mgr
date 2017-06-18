package com.mgr2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Training;
import com.mgr2.model.User;

@Repository("trainingRepository")
public interface TrainingRepository extends JpaRepository<Training, Integer> {
	
	Training findByName(String name);
	Training findById(int id);
	
	List<Training> findByUserId(int id);
	
	@Query("SELECT t.id FROM Training t WHERE t.name=:name")
	int findTrainingIdByName(@Param("name") String name);
	
	@Query("SELECT t.name FROM Training t WHERE t.id=:id")
	String findTrainingNameById(@Param("id") int id);

}
