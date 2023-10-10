package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FOLLOWER")
public class Follow implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FOLLOWERID")
	private int followerId;

	@Column(name = "FOLLOWDATE")
	private Date followDate;

	@ManyToOne
	@JoinColumn(name = "ACOUNT_A")
	private Author authorsAccountA;

	@ManyToOne
	@JoinColumn(name = "ACOUNT_B")
	private Author authorsAccountB;
}
