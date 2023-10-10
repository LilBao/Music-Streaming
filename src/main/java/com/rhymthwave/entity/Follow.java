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

//	@JsonIgnore
//	@OneToMany(mappedBy = "followerA")
//	private List<Author> authorsAccountA;
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "followerB")
//	private List<Author> authorsAccountB;
}
