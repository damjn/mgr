package com.mgr2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Content;

@Repository("contentRepository")
public interface ContentRepository extends JpaRepository<Content, Integer>{
	
	List<Content> findByTrainingId(int id);
	Content findById(int id);
}
