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
@Table(name = "ARTIST")
public class Artist implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ARTISTID")
	private long artistId;

	@Column(name = "ARTISTNAME")
	private String artistName;

	@Column(name = "DATEOFBIRTH")
	private Date dateOfBirth;

	@Column(name = "FULLNAME")
	private String fullName;

	@Column(name = "PLACEOFBIRTH")
	private String placeOfBirth;

	@Column(name = "BIO")
	private String bio;

	@Column(name = "IMAGEGALLERY")
	private String[] imagesGallery;
	
	@Column(name = "PUBLICIDIMAGEGALLERY")
	private String[] publicIdImageGallery;

	@Column(name = "SOCIALMEDIALINKS")
	private String[] socialMediaLinks;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "VERIFY")
	private Boolean isVerify;

	@Column(name = "DATESTARTED")
	private Date dateStarted;

	@OneToOne
	@JoinColumn(name = "PROFILEIMAGE")
	private Image imagesProfile;

	@OneToOne
	@JoinColumn(name = "BACKGROUDIMAGE")
	private Image backgroundImage;

	@OneToOne
	@JoinColumn(name = "EMAIL")
	private Account account;

	@JsonIgnore
	@OneToMany(mappedBy = "artist")
	private List<Report> reports;

	@JsonIgnore
	@OneToMany(mappedBy = "artist")
	private List<Album> albums;

	@JsonIgnore
	@OneToMany(mappedBy = "artist")
	private List<Writter> writters;
}
