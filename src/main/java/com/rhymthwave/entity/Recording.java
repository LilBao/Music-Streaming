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
@Table(name = "RECORDING")
public class Recording implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECORDINGID")
	private long recordingId;

	@Column(name = "RECORDINGIDNAME")
	private String recordingName;

	@Column(name = "AUDIOFILEURL")
	private String audioFileUrl;
	
	@Column(name = "PUBLICIDAUDIOFILE")
	private String publicIdAudio;

	@Column(name = "LYRICSURL")
	private String lyricsUrl;

	@Column(name = "PUBLICIDLYRICS")
	private String publicIdLyrics;
	
	@Column(name = "LIKES")
	private long likes;

	@Column(name = "DURATION")
	private int duration;

	@Column(name = "SONGSTYLE")
	private String songStyle;

	@Column(name = "MOOD")
	private String mood;

	@Column(name = "CULTURE")
	private String culture;

	@Column(name = "INSTRUMENT")
	private String instrument;

	@Column(name = "VERSIONS")
	private String versions;

	@Column(name = "STUDIO")
	private String studio;

	@Column(name = "IDMV")
	private String idMv;

	@Column(name = "PRODUCE")
	private String produce;

	@Column(name = "RECORDINGDATE")
	private Date recordingdate = new Date();

	@Column(name = "ISDELETED")
	private boolean isDeleted;
	
	@Column(name = "EMAILCREATE")
	private String emailCreate;


	@ManyToOne
	@JoinColumn(name = "SONGSID")
	private Song song;

	@JsonIgnore
	@OneToMany(mappedBy = "recording")
	private List<Monitor> monitors;

	@JsonIgnore
	@OneToMany(mappedBy = "recording")
	private List<Wishlist> wishlists;

	@JsonIgnore
	@OneToMany(mappedBy = "recording")
	private List<PlaylistRecord> playlistRecords;

	@JsonIgnore
	@OneToMany(mappedBy = "recording")
	private List<Report> reports;

	@JsonIgnore
	@OneToMany(mappedBy = "recording")
	private List<Track> tracks;
	
	@JsonIgnore
	@OneToMany(mappedBy = "recording")
	private List<SongGenre> songGenres;
	
}
