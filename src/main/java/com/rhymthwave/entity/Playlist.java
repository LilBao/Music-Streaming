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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PLAYLISTS")
public class Playlist implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYLISTID")
	private Long playlistId;

	@Column(name = "PLAYLISTNAME")
	private String playlistName;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "ISPUBLIC")
	private Boolean isPublic = true;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATEDATE")
	@Temporal(TemporalType.DATE)
	private Date createDate = new Date();

	@ManyToOne
	@JoinColumn(name = "USERTYPEID")
	private UserType usertype;

	@OneToOne
	@JoinColumn(name = "IMAGE")
	private Image image;

	@JsonIgnore
	@OneToMany(mappedBy = "playlist")
	private List<PlaylistRecord> playlistRecords;

	@JsonIgnore
	@OneToMany(mappedBy = "playlist")
	private List<Playlist_Podcast> playlistPodcast;
}
