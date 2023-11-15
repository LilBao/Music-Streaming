package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Episode;

@Repository
public interface EpisodeDAO extends JpaRepository<Episode, Long>{
	@Query("select o from Episode o where o.podcast.podcastId = :podcastId and o.isDelete = :status")
	List<Episode> findAllEpisodeByPodcast(@Param("podcastId") Long podcastId,@Param("status") Boolean status);
	
	@Query(value="SELECT TOP 1 * FROM EPISODES WHERE PUBLISHDATE < GETDATE() AND ISPUBLIC= 1 AND ISDELETED = 0 AND PODCASTID = :podcastId ORDER BY PUBLISHDATE DESC",nativeQuery = true)
	Episode findLatestByPodcast(@Param("podcastId") Long podcastId);

	@Query(value = "select * from episodes where episodesid= :episodeId", nativeQuery = true)
	Episode findAllById(@Param("episodeId") Long episodeId);
}
