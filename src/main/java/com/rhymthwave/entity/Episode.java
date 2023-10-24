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
@Table(name = "EPISODES")
public class Episode implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EPISODESID")
	private long episodeId;

	@Column(name = "PUBLICIDFILE")
	private String publicIdFile;
	
	@Column(name = "FILEURL")
	private String fileUrl;

	@Column(name = "EPISODESTITLE")
	private String episodeTitle;

	@Column(name = "DESCRIPTIONS")
	private String description;

	@Column(name = "PUBLISHDATE")
	private Date publishDate;

	@Column(name = "SESSIONNUMBER")
	private Integer sessionNumber;

	@Column(name = "EPNUMBER")
	private Integer episodeNumber;

	@Column(name = "EPTYPE")
	private String episodeType;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "ISPUBLIC")
	private boolean isPublic;
	
	@ManyToOne
	@JoinColumn(name = "PODCASTID")
	private Podcast podcast;

	@ManyToOne
	@JoinColumn(name = "IMAGEEP")
	private Image image;

	@JsonIgnore
	@OneToMany(mappedBy = "episode")
	private List<Playlist_Podcast> playlistPodcast;

	@JsonIgnore
	@OneToMany(mappedBy = "episode")
	private List<Report> reports;
}
