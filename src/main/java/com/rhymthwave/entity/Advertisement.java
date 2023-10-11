package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;

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
	private long adId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "URL")
	private String url;

	@Column(name = "STARTDATE")
	private Date startDate;

	@Column(name = "ENDDATE")
	private Date endDate;

	@Column(name = "BUDGET")
	private float budget;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TARGETAUDIENCE")
	private String targetAudience;

	@Column(name = "CLICKED")
	private long clicked;

	@Column(name = "PRIORITY")
	private int priority;

	@Column(name = "TAG")
	private String tag;

	@ManyToOne
	@JoinColumn(name = "BANNER")
	private Image images;

	@ManyToOne
	@JoinColumn(name = "ACCOUNTID")
	private Account account;
}
