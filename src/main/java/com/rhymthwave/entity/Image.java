package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "IMAGES")
public class Image implements Serializable {

	@Id
	@Column(name = "ACCESSID")
	private String accessId;

	@Column(name = "URL")
	private String url;
	
	@Column(name = "WEIGHT")
	private Integer weight;

	@Column(name = "HEIGHT")
	private Integer height;

	@JsonIgnore
	@OneToMany(mappedBy = "images")
	private List<Episode> episodes;

}
