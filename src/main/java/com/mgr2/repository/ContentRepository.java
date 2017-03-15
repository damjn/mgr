package com.mgr2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr2.model.Content;
import com.mgr2.model.Role;

@Repository("contentRepository")
public interface ContentRepository extends JpaRepository<Content, Integer>{

}
