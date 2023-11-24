package com.rhymthwave.DAO;

import java.util.List;

import com.rhymthwave.DTO.CountStatusADS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Advertisement;

@Repository
public interface AdvertismentDAO extends JpaRepository<Advertisement, Long>{
	
	@Query("Select o from Advertisement o where o.audioFile is not null and o.endDate > CURRENT_DATE and o.active=true order by o.priority asc, o.listened asc")
	List<Advertisement> findAdsAudioNotNull();

	//2. running
	//3. completed
	@Query("select a from Advertisement a  where a.status = 2 or a.status = 3")
	List<Advertisement> findAllAdvertisementRunningAndCompleted();
	// 1.pending
	// 4.reject
	@Query("select a from Advertisement a  where a.status = 1 or a.status = 4")
	List<Advertisement> findAllAdvertisementPendingAndReject();

	@Query(value = "SELECT new com.rhythmwave.DTO.CountStatusADS("
			+ "(SELECT COUNT(*) FROM advertisement adv1 WHERE adv1.status = 1 AND adv1.idads = A.idads), "
			+ "(SELECT COUNT(*) FROM advertisement adv2 WHERE adv2.status = 4 AND adv2.idads = A.idads)) "
			+ "FROM advertisement A", nativeQuery = true)
	List<CountStatusADS> findAllAdvertisementPendingAndRejectByStatus();


}
