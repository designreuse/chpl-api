package gov.healthit.chpl.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gov.healthit.chpl.dto.CorrectiveActionPlanCertificationResultDTO;
import gov.healthit.chpl.dto.CorrectiveActionPlanDTO;

public class CorrectiveActionPlanDetails {
	
	private Long id;
	private Long certifiedProductId;
	private String acbSummary;
	private String developerSummary;
	private Date approvalDate;
	private Date effectiveDate;
	private Date estimatedCompletionDate;
	private Date noncomplianceDate;
	private Date actualCompletionDate;
	private String resolution;
	private List<CorrectiveActionPlanCertificationResult> certifications;
	
	public CorrectiveActionPlanDetails() {
		this.certifications = new ArrayList<CorrectiveActionPlanCertificationResult>();
	}
	public CorrectiveActionPlanDetails(CorrectiveActionPlanDTO dto) {
		this.id = dto.getId();
		this.certifiedProductId = dto.getCertifiedProductId();
		this.acbSummary = dto.getAcbSummary();
		this.developerSummary = dto.getDeveloperSummary();
		this.approvalDate = dto.getApprovalDate();
		this.effectiveDate = dto.getEffectiveDate();
		this.estimatedCompletionDate = dto.getEstimatedCompletionDate();
		this.noncomplianceDate = dto.getNoncomplainceDate();
		this.actualCompletionDate = dto.getActualCompletionDate();
		this.resolution = dto.getResolution();
		
		this.certifications = new ArrayList<CorrectiveActionPlanCertificationResult>();
	}
	public CorrectiveActionPlanDetails(CorrectiveActionPlanDTO dto, List<CorrectiveActionPlanCertificationResultDTO> certDtos) {
		this.id = dto.getId();
		this.certifiedProductId = dto.getCertifiedProductId();
		this.acbSummary = dto.getAcbSummary();
		this.developerSummary = dto.getDeveloperSummary();
		this.approvalDate = dto.getApprovalDate();
		this.effectiveDate = dto.getEffectiveDate();
		this.estimatedCompletionDate = dto.getEstimatedCompletionDate();
		this.noncomplianceDate = dto.getNoncomplainceDate();
		this.actualCompletionDate = dto.getActualCompletionDate();
		this.resolution = dto.getResolution();
		
		this.certifications = new ArrayList<CorrectiveActionPlanCertificationResult>();
		if(certDtos != null && certDtos.size() > 0) {
			for(CorrectiveActionPlanCertificationResultDTO certDto : certDtos) {
				CorrectiveActionPlanCertificationResult currCert = new CorrectiveActionPlanCertificationResult(certDto);
				this.certifications.add(currCert);
			}
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCertifiedProductId() {
		return certifiedProductId;
	}
	public void setCertifiedProductId(Long certifiedProductId) {
		this.certifiedProductId = certifiedProductId;
	}
	public String getAcbSummary() {
		return acbSummary;
	}
	public void setAcbSummary(String acbSummary) {
		this.acbSummary = acbSummary;
	}
	public String getDeveloperSummary() {
		return developerSummary;
	}
	public void setDeveloperSummary(String developerSummary) {
		this.developerSummary = developerSummary;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getActualCompletionDate() {
		return actualCompletionDate;
	}
	public void setActualCompletionDate(Date actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public List<CorrectiveActionPlanCertificationResult> getCertifications() {
		return certifications;
	}
	public void setCertifications(List<CorrectiveActionPlanCertificationResult> certifications) {
		this.certifications = certifications;
	}
	
	public void setCertificationDtos(List<CorrectiveActionPlanCertificationResultDTO> certDtos) {
		if(certDtos != null && certDtos.size() > 0) {
			for(CorrectiveActionPlanCertificationResultDTO certDto : certDtos) {
				CorrectiveActionPlanCertificationResult currCert = new CorrectiveActionPlanCertificationResult(certDto);
				this.certifications.add(currCert);
			}
		}
	}
	public Date getEstimatedCompletionDate() {
		return estimatedCompletionDate;
	}
	public void setEstimatedCompletionDate(Date estimatedCompletionDate) {
		this.estimatedCompletionDate = estimatedCompletionDate;
	}
	public Date getNoncomplianceDate() {
		return noncomplianceDate;
	}
	public void setNoncomplianceDate(Date noncomplianceDate) {
		this.noncomplianceDate = noncomplianceDate;
	}
}