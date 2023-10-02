package com.rhymthwave.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHORID")
	private long authorId;

	@ManyToOne
	@JoinColumn(name = "IDROLE")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private Account accounts;

	@ManyToOne
	@JoinColumn(name = "ACOUNT_A")
	private Follow followerA;

	@ManyToOne
	@JoinColumn(name = "ACOUNT_B")
	private Follow followerB;
}
