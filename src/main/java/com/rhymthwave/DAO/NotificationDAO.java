package com.rhymthwave.DAO;

import com.rhymthwave.entity.Mood;
import com.rhymthwave.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDAO extends JpaRepository<Notification, Integer>{

}
