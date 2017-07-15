package com.mgr2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Content;

@Repository("contentRepository")
public interface ContentRepository extends JpaRepository<Content, Integer>{
	
	List<Content> findByTrainingId(int id);
	Content findById(int id);
//	@Query("SELECT c FROM Content c WHERE c.training_id=:training_id and c.order_nr=:order_nr")
//	Content findByTrainingIdAndOrderNr(@Param("training_id") int training_id, @Param("order_nr") int order_nr);
	
	Content findByTrainingIdAndOrdernr(int id, int order_nr);
}
