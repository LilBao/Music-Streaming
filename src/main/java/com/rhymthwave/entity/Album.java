package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "ALBUM")
public class Album implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALBUMID")
	private long albumId;

	@Column(name = "ALBUMNAME")
	private String albumName;

	@Column(name = "RELEASEDATE")
	private Date releaseDate;
	
	@Column(name = "DESCRIPTIONS")
	private String description;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne
	@JoinColumn(name = "COVERIMAGE")
	private Image image;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne
	@JoinColumn(name = "ARTISTID")
	private Artist artist;

	
	@JsonIgnore
	@OneToMany(mappedBy = "album")
	private List<Track> tracks;

}
