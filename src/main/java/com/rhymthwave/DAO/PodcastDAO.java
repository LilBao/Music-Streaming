package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rhymthwave.entity.Podcast;
@Repository
public interface PodcastDAO extends JpaRepository<Podcast, Long>{
	@Query("select o from Podcast o where o.account.email = :email")
	List<Podcast> findMyPobcast(@Param("email") String email);
}
