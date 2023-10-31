package com.rhymthwave.entity;

import java.io.Serializable;
import java.util.Date;

import com.rhymthwave.entity.TypeEnum.EROLE;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NEWS")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name="filter.News",
		procedureName = "sp_filter",
		resultClasses = {Object.class},
		parameters = { @StoredProcedureParameter(name="month", type = Integer.class),
					  @StoredProcedureParameter(name="year", type = Integer.class)})
})
public class News implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDNEWS")
	private long newsId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT ")
	private String content;

	@Column(name = "PUBLISHDATE")
	private Date publishDate;

	@Column(name = "LASTMODIFIED")
	private Date lastModified;

	@Column(name = "CREATEDATE")
	private Date createDate;
	
	@Column(name = "CREATEFOR")
	@Enumerated(EnumType.STRING)
	private EROLE createFor;
	
	@Column(name = "MODIFIEDBY", length = 255)
	private String modifiedBy;
	
	@Column(name = "MODIFIDATE")
	private Date modifiDate;
	
	
	@OneToOne( cascade = CascadeType.REMOVE)
	@JoinColumn(name = "IMAGE")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "AUTHORID")
	private Account account;
}