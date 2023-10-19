package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "COUNTRY")
public class Country implements Serializable {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "NAMECOUNTRY")
	private String nameCountry;
	
	@Column(name = "CREATEBY",length = 255)
	private String createBy;
	
	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "MODIFIEDBY", length = 255)
	private String modifiedBy;
	
	@Column(name = "MODIFIDATE")
	private Date modifiDate;
	
}
