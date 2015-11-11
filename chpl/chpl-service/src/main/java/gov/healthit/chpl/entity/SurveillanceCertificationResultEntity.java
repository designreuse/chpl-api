package gov.healthit.chpl.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="surveillance_certification_result")
public class SurveillanceCertificationResultEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "surveillance_certification_resultSurveillance_certification_result_idGenerator")
	@Basic( optional = false )
	@Column( name = "surveillance_certification_result_id", nullable = false  )
	@SequenceGenerator(name = "surveillance_certification_resultSurveillance_certification_result_idGenerator", 
		sequenceName = "surveillance_certification_result_surveillance_certification_result_id_seq", 
		allocationSize = 1)
	private Long id;
	
	@Basic( optional = false )
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "surveillance_id", unique=true, nullable = true)
	private SurveillanceEntity surveillance;
	
	@Basic( optional = false )
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "certification_criterion_id", unique=true, nullable = true)
	private CertificationCriterionEntity certCriterion;
	
	@Column(name="pass")
	private Boolean passes;

	@Basic( optional = false )
	@Column( name = "creation_date", nullable = false  )
	private Date creationDate;
	
	@Basic( optional = false )
	@Column( name = "last_modified_date", nullable = false  )
	private Date lastModifiedDate;
	
	@Basic( optional = false )
	@NotNull()
	@Column( name = "last_modified_user", nullable = false  )
	private Long lastModifiedUser;
	
	@Basic( optional = false )
	@NotNull()
	@Column( nullable = false  )
	private Boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(Long lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public SurveillanceEntity getSurveillance() {
		return surveillance;
	}

	public void setSurveillance(SurveillanceEntity surveillance) {
		this.surveillance = surveillance;
	}

	public CertificationCriterionEntity getCertCriterion() {
		return certCriterion;
	}

	public void setCertCriterion(CertificationCriterionEntity certCriterion) {
		this.certCriterion = certCriterion;
	}

	public Boolean getPasses() {
		return passes;
	}

	public void setPasses(Boolean passes) {
		this.passes = passes;
	}

}
