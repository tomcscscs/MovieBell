package org.edupoll.app.repository;

import java.util.Optional;

import org.edupoll.app.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts, Integer> {
	
	public Optional<Accounts> findByUsername(String username);
	
	
	

}
