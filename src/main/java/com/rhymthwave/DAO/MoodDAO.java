package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rhymthwave.entity.Mood;

public interface MoodDAO  extends JpaRepository<Mood, Integer>{

}
