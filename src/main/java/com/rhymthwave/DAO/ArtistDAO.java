package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Artist;

@Repository
public interface ArtistDAO extends JpaRepository<Artist, Integer>{
	@Query("Select o from Artist o where account.email = :email")
	Artist findByEmail(@Param("email") String email);
}
