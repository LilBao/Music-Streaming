package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Table(name = "PODCASTS")
public class Podcast implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PODCASTID")
	private Integer podcastId;

	@Column(name = "PODCASTNAME",columnDefinition = "nvarchar(55)")
	private String podcastName;

	@Column(name = "BIO",columnDefinition = "nvarchar(max)")
	private String bio;

	@Column(name = "SOCIALMEDIALINK",columnDefinition = "varchar(max)")
	private String socialMediaLink;

	@Column(name = "RELEASEDATE")
	private Date releaseDate;

	@Column(name = "LANGUAGE",columnDefinition = "nvarchar(55)")
	private String language;
	
	@Column(name = "RATE")
	private Integer rate;

	@Column(name = "AUTHORNAME",columnDefinition = "nvarchar(100)")
	private String authorName;
	
	@OneToOne
	@JoinColumn(name = "ACCESSID")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "TAGID")
	private Tag tag;

	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private Account account;

	@JsonIgnore
	@OneToMany(mappedBy = "podcast",cascade = CascadeType.ALL)
	private List<Episode> Episodes;

	@JsonIgnore
	@OneToMany(mappedBy = "podcastId",cascade = CascadeType.ALL)
	private List<Report> reports;
}
