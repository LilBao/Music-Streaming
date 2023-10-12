package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Artist;

@Repository
public interface ArtistDAO extends JpaRepository<Artist, Integer>{
	@Query("Select o from Artist o where account.email = :email")
	Artist findByEmail(@Param("email") String email);
	
	@Query("Select o from Artist o where o.isVerify = :verify")
	List<Artist> findAllIsVerify(@Param("verify") Boolean verify);
}
