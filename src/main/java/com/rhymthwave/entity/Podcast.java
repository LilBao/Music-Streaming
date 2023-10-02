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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PODCAST")
public class Podcast implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PODCASTID")
	private long podcastId;

	@Column(name = "PODCASTNAME")
	private String podcastName;

	@Column(name = "BIO")
	private String bio;

	@Column(name = "SOCIALMEDIALINK")
	private String socialMediaLink;

	@Column(name = "RELEASEDATE")
	private Date releaseDate;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "VERIFY")
	private boolean verify;

	@OneToOne
	@JoinColumn(name = "IMGAGEID")
	private Image images;

	@ManyToOne
	@JoinColumn(name = "CATEGORY")
	private Tag tagId;

	@ManyToOne
	@JoinColumn(name = "ACOUNTID")
	private Account accounts;

	@JsonIgnore
	@OneToMany(mappedBy = "podcastId")
	private List<Episode> episodeos;

	@JsonIgnore
	@OneToMany(mappedBy = "podcastId")
	private List<Report> reports;
}
