package org.edupoll.app.repository;

import java.util.List;
import java.util.Optional;

import org.edupoll.app.entity.Picks;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PicksRepository extends JpaRepository<Picks, Integer> {
	
	List<Picks> findByAccountsUsername(String username);
	
	Integer countByAccountsId(int id);
	
	Optional<Picks> findByAccountsUsernameAndMovieId(String userName, Integer movieId);
	
	
	
	
	

}
