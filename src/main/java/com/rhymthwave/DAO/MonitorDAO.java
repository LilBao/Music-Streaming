package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.DTO.AnalysisDTO;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Monitor;
import com.rhymthwave.entity.Recording;

@Repository
public interface MonitorDAO extends JpaRepository<Monitor, Long>{
	Monitor findByRecordingAndAccount(Recording recording, Account account);
	
	@Query(value ="select count(*) as quantity, (YEAR(GETDATE()) - YEAR(a.birthday)) as age from monitor m "
			+ "join accounts a on a.email= m.account "
			+ "join recording r on r.recordingid = m.recordingid "
			+ "where r.recordingid = :recordingid and  GETDATE() - :dateMonitor <= m.datemonitor and m.datemonitor <= GETDATE() "
			+ "group by (YEAR(GETDATE()) - YEAR(a.birthday))",nativeQuery = true)
	List<AnalysisDTO> analysisRecordingAge(@Param("recordingid") Long recordingid, @Param("dateMonitor") Integer dateMonitor);
	
	@Query(value ="select count(*) as quantity, a.country as country from monitor m "
			+ "join accounts a on a.email= m.account "
			+ "join recording r on r.recordingid = m.recordingid "
			+ "where r.recordingid = :recordingid and  GETDATE() - :dateMonitor <= m.datemonitor and m.datemonitor <= GETDATE() "
			+ "group by a.country",nativeQuery = true)
	List<AnalysisDTO> analysisRecordingCountry(@Param("recordingid") Long recordingid, @Param("dateMonitor") Integer dateMonitor);
	
	@Query(value ="select count(*) as quantity, a.gender as gender from monitor m "
			+ "join accounts a on a.email= m.account "
			+ "join recording r on r.recordingid = m.recordingid "
			+ "where r.recordingid = :recordingid and  GETDATE() - :dateMonitor <= m.datemonitor and m.datemonitor <= GETDATE() "
			+ "group by a.gender",nativeQuery = true)
	List<AnalysisDTO> analysisRecordingGender(@Param("recordingid") Long recordingid, @Param("dateMonitor") Integer dateMonitor);
	
}
