package org.edupoll.app.repository;

import org.edupoll.app.entity.Picks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjaxRepository extends JpaRepository<Picks, Integer> {
	
	

}
