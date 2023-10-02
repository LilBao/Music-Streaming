package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "IMAGES")
public class Image implements Serializable {

	@Id
	@Column(name = "ACCESSID")
	private String accessId;

	@Column(name = "URL")
	private String url;

	@Column(name = "HEIGHT")
	private float height;

	@Column(name = "WEIGHT")
	private float weight;

	@OneToOne(mappedBy = "images")
	private Account accounts;

	@OneToOne(mappedBy = "images")
	private News news;

	@OneToOne(mappedBy = "images")
	private Advertisement advertisement;

	@OneToOne(mappedBy = "images")
	private Playlist playlist;

	@JsonIgnore
	@OneToMany(mappedBy = "images")
	private List<Episode> episodes;

	@OneToOne(mappedBy = "images")
	private Podcast podcastId;

	@OneToOne(mappedBy = "images")
	private Album albumId;

	@OneToOne(mappedBy = "imagesG")
	private Artist artistIdImgG;

	@OneToOne(mappedBy = "imagesP")
	private Artist artistIdImgP;
	
	@OneToOne(mappedBy = "images")
	private Song songId;
}
