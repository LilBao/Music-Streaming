package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@Column(name = "EMAIL",columnDefinition = "varchar(250)",nullable = false)
	private String email;

	@Column(name = "PASSWORD",columnDefinition = "varchar(500)")
	private String password;

	@Column(name = "USENAME",columnDefinition = "nvarchar(55)")
	private String username;

	@Column(name = "BIRTHDAY")
	private Date birthday;

	@Column(name = "GENDER")
	private int gender;

	@Column(name = "COUNTRY",columnDefinition = "nvarchar(55)")
	private String country;

	@Column(name = "ISVERIFY")
	private boolean isVerify;

	@Column(name = "VERIFYCATIONCODE",columnDefinition = "varchar(250)")
	private String verificationCode;

	@Column(name = "VERIFYCATIONCODEEXPIRES")
	private Date verificationCodeExpires;

	@Column(name = "REMAININGVERIFYCATION")
	private int remainingVerification;

	@Column(name = "ISBLOCKED")
	private boolean isBlocked;

	@Column(name = "REFRESHTOKEN",columnDefinition = "varchar(max)")
	private String refreshToken;

	@OneToOne
	@JoinColumn(name = "IMAGEID")
	private Image image;

	@JsonIgnore
	@OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
	private List<Author> author;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<News> news;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Monitor> monitor;

	@OneToMany(mappedBy = "account")
	private List<UserType> userType;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Advertisement> advertisement;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Podcast> podcast;

	@JsonIgnore
	@OneToOne(mappedBy = "account")
	private Artist artist;
}
