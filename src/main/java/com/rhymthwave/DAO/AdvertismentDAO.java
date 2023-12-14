package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Advertisement;

@Repository
public interface AdvertismentDAO extends JpaRepository<Advertisement, Long>{
	
	@Query("Select o from Advertisement o where o.audioFile is not null and o.endDate > CURRENT_DATE and o.active=true and o.status=2 order by o.priority asc, o.listened asc")
	List<Advertisement> findAdsAudioNotNull();
	
	@Query("Select o from Advertisement o where o.account.email=:email order by o.endDate asc")
	List<Advertisement> findAdsByEmail(@Param("email") String email);
	
	@Query("Select o from Advertisement o where o.status = :status and o.endDate > CURRENT_DATE and o.active= :active order by o.priority asc, o.listened, o.clicked asc")
	List<Advertisement> findAdsRunning(@Param("active") Boolean active,@Param("status") Integer status);
}
