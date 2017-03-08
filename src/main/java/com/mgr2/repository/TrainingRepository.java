package com.mgr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Training;

@Repository("trainingRepository")
public interface TrainingRepository extends JpaRepository<Training, Long> {
	Training findByName(String name);

}
