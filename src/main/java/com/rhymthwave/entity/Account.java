package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

	@Id
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USENAME")
	private String username;

	@Column(name = "BIRTHDAY")
	private Date birthday;

	@Column(name = "GENDER")
	private int gender;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "ISVERIFY")
	private boolean isVerify;

	@Column(name = "VERIFYCATIONCODE")
	private String verificationCode;

	@Column(name = "VERIFYCATIONCODEEXPIRES")
	private Date verificationCodeExpires;

	@Column(name = "REMAININGVERIFYCATION")
	private int remainingVerification;

	@Column(name = "ISBLOCKED")
	private boolean isBlocked;

	@Column(name = "REFRESHTOKEN")
	private String refreshToken;

	@OneToOne
	@JoinColumn(name = "IMAGEID")
	private Image images;

	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	private List<Author> authors;

	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	private List<News> news;

	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	private List<Monitor> monitors;

	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	private List<UserType> userTypes;

	@JsonIgnore
	@OneToMany(mappedBy = "accounts")
	private List<Advertisement> advertisements;

	@JsonIgnore
	@OneToOne(mappedBy = "accounts")
	private Podcast podcasts;

	@JsonIgnore
	@OneToOne(mappedBy = "accounts")
	private Artist artists;
}
