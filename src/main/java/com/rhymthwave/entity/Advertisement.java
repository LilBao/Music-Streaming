package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "ADVERTISEMENT ")
public class Advertisement implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDADS")
	private Integer adId;

	@Column(name = "TITLE",columnDefinition = "nvarchar(255)")
	private String title;

	@Column(name = "CONTENT",columnDefinition = "nvarchar(255)")
	private String content;

	@Column(name = "URL",columnDefinition = "nvarchar(max)")
	private String url;

	@Column(name = "STARTDATE")
	private Date startDate;

	@Column(name = "ENDDATE")
	private Date endDate;

	@Column(name = "BUDGET")
	private float budget;

	@Column(name = "STATUS",columnDefinition = "nvarchar(55)")
	private String status;

	@Column(name = "TARGETAUDIENCE",columnDefinition = "nvarchar(55)")
	private String targetAudience;

	@Column(name = "CLICKED")
	private long clicked;

	@Column(name = "PRIORITY")
	private int priority;

	@Column(name = "AUDIOFILE",columnDefinition = "nvarchar(max)")
	private String audioFile;
	
	@Column(name = "PUBLICIDAUDIO",columnDefinition = "nvarchar(max)")
	private String  publicIdAudio;
	
	@Column(name = "TAG",columnDefinition = "varchar(55)")
	private String tag;

	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "MODIFIEDBY",columnDefinition = "nvarchar(255)")
	private String modifiedBy;
	
	@Column(name = "MODIFIDATE")
	private Date modifiDate;
	
	@ManyToOne
	@JoinColumn(name = "BANNER")
	private Image image;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ACCOUNTID")
	private Account account;
}
