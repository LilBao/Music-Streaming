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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SUBCRIPTIONS")
public class Subscription implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBCRIPTIONID")
	private long subscriptionId;
	
	@Column(name = "SUBCRIPTIONTYPE")
	private int subscriptionType;
	
	@Column(name = "PRICE")
	private float price;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "DURATION")
	private int duration;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subscription")
	private List<UserType> userTypes;
}
