package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Monitor;
import com.rhymthwave.entity.Recording;

@Repository
public interface MonitorDAO extends JpaRepository<Monitor, Long>{
	Monitor findByRecordingAndAccount(Recording recording, Account account);
}
