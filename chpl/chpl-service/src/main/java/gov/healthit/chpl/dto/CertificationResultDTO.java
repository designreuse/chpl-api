package gov.healthit.chpl.dto;

import gov.healthit.chpl.entity.CertificationResultEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertificationResultDTO {
	
	private Long id;
	private Long certificationCriterionId;
	private Long certifiedProductId;
	private Date creationDate;
	private Boolean deleted;
	private Boolean gap;
	private Boolean sed;
	private Boolean successful;
	private Boolean g1Success;
	private Boolean g2Success;
	private String ucdProcessSelected;
	private String ucdProcessDetails;
	private Date lastModifiedDate;
	private Long lastModifiedUser;
	private List<CertificationResultAdditionalSoftwareDTO> additionalSoftware;
	
	public CertificationResultDTO(){
		additionalSoftware = new ArrayList<CertificationResultAdditionalSoftwareDTO>();
	}
	
	public CertificationResultDTO(CertificationResultEntity entity){
		super();
		this.id = entity.getId();
		this.certificationCriterionId = entity.getCertificationCriterionId();
		this.certifiedProductId = entity.getCertifiedProductId();
		this.creationDate = entity.getCreationDate();
		this.gap = entity.isGap();
		this.sed = entity.getSed();
		this.g1Success = entity.getG1Success();
		this.g2Success = entity.getG2Success();
		this.ucdProcessSelected = entity.getUcdProcessSelected();
		this.ucdProcessDetails = entity.getUcdProcessDetails();
		this.successful = entity.isSuccess();
		this.deleted = entity.getDeleted();
		this.lastModifiedDate = entity.getLastModifiedDate();
		this.lastModifiedUser = entity.getLastModifiedUser();
	}
	
	public Long getCertificationCriterionId() {
		return certificationCriterionId;
	}
	public void setCertificationCriterionId(Long certificationCriterionId) {
		this.certificationCriterionId = certificationCriterionId;
	}
	public Long getCertifiedProductId() {
		return certifiedProductId;
	}
	public void setCertifiedProduct(Long certifiedProductId) {
		this.certifiedProductId = certifiedProductId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getGap() {
		return gap;
	}
	public void setGap(Boolean gap) {
		this.gap = gap;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getSuccessful() {
		return successful;
	}
	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public List<CertificationResultAdditionalSoftwareDTO> getAdditionalSoftware() {
		return additionalSoftware;
	}

	public void setAdditionalSoftware(List<CertificationResultAdditionalSoftwareDTO> list) {
		this.additionalSoftware = list;
	}

	public Boolean getSed() {
		return sed;
	}

	public void setSed(Boolean sed) {
		this.sed = sed;
	}

	public Boolean getG1Success() {
		return g1Success;
	}

	public void setG1Success(Boolean g1Success) {
		this.g1Success = g1Success;
	}

	public Boolean getG2Success() {
		return g2Success;
	}

	public void setG2Success(Boolean g2Success) {
		this.g2Success = g2Success;
	}

	public String getUcdProcessSelected() {
		return ucdProcessSelected;
	}

	public void setUcdProcessSelected(String ucdProcessSelected) {
		this.ucdProcessSelected = ucdProcessSelected;
	}

	public String getUcdProcessDetails() {
		return ucdProcessDetails;
	}

	public void setUcdProcessDetails(String ucdProcessDetails) {
		this.ucdProcessDetails = ucdProcessDetails;
	}

	public void setCertifiedProductId(Long certifiedProductId) {
		this.certifiedProductId = certifiedProductId;
	}
	
}
