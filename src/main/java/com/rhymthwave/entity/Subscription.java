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
	private Integer subscriptionId;
	
	@Column(name = "SUBCRIPTIONTYPE")
	private String subscriptionType;
	
	@Column(name = "PRICE",columnDefinition = "Float")
	private Float price;
	
	@Column(name = "DESCRIPTION",columnDefinition = "nvarchar(max)")
	private String description;
	
	@Column(name = "PRDSTRIPEID")
	private String prdStripeId;
	
	@Column(name = "PRDPAYPALID")
	private String prdPaypalId;
	
	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "DURATION")
	private Integer duration;
	
	@Column(name = "PLAYLISTALLOW")
	private Integer playlistAllow;
	
	@Column(name = "NIP")
	private Integer nip;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subscription")
	private List<UserType> userTypes;
}

