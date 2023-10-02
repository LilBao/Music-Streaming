package com.rhymthwave.entity;

import java.io.Serializable;
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
@Table(name = "PLAYLIST_PODCAST")
public class Playlist_Podcast implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYLIST_PODCASTID")
	private long playlistPodcastId;

	@ManyToOne
	@JoinColumn(name = "PLAYLISTID")
	private Playlist playlistId;

	@ManyToOne
	@JoinColumn(name = "EPISODESID")
	private Episode episodeId;

	@JsonIgnore
	@OneToMany(mappedBy = "playlistPodcastId")
	private List<Access> accesses;

}
