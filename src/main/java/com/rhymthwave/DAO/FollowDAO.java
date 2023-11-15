package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Follow;
import java.util.List;


@Repository
public interface FollowDAO extends JpaRepository<Follow, Long>{
	
	@Query("select o from Follow o where o.authorsAccountA = :accountA and o.authorsAccountB = :accountB")
	Follow findFollowByAccount(@Param("accountA") Author accountA, @Param("accountB") Author accountB);
	
	List<Follow> findByAuthorsAccountA(Author authorsAccountA);
	
	@Query("SELECT o FROM Follow o WHERE o.authorsAccountB.authorId = :authorId")
	List<Follow> findByAuthorsAccountB(@Param("authorId") Long authorId);

	@Query(value="select count(*) from follower where account_b = :authorId and followdate > GETDATE() - :days",nativeQuery = true)
	Integer getQuantityFollowByDate(@Param("authorId") Long authorId, @Param("days") Integer days);
	
}
