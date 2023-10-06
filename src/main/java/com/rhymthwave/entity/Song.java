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
@Table(name = "SONGS")
public class Song implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SONGSID")
	private int songId;

	@Column(name = "SONGNAME")
	private String songName;

	@OneToOne
	@JoinColumn(name = "IMAGEID")
	private Image images;

	@Column(name = "REALEASEDAY")
	private Date releaseDay;

	@Column(name = "ISDELETED")
	private boolean isDeleted;

	@JsonIgnore
	@OneToMany(mappedBy = "songId")
	private List<Recording> recordings;

	@JsonIgnore
	@OneToMany(mappedBy = "songId")
	private List<Writter> writters;

}
