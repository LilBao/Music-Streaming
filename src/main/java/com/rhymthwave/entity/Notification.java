package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NOTIFICATION ")
public class Notification implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTIFICATIONID")
	private int notificationId;
	
	@Column(name = "TITILE")
	private String title;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "CREATEBY",length = 255)
	private String createBy;
	
	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "MODIFIEDBY", length = 255)
	private String modifiedBy;
	
	@Column(name = "MODIFIDATE")
	private Date modifiDate;
}
