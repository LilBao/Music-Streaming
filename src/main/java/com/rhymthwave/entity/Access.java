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
@Table(name = "ACCESS")
public class Access implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCESSID")
	private int accessId;

	@ManyToOne
	@JoinColumn(name = "PLAYLIST_RECORDINGID")
	private PlaylistRecord playlistRecordingId;

	@ManyToOne
	@JoinColumn(name = "USERTYPEID")
	private UserType usertype;

	@ManyToOne
	@JoinColumn(name = "EPISODESID")
	private Playlist_Podcast playlistPodcastId;

}
