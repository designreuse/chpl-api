package gov.healthit.chpl.dto;

import gov.healthit.chpl.entity.ProductEntity;
import gov.healthit.chpl.entity.ProductVersionEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductEntityDTO {
	
	private Long id;
	private Date creationDate;
	private Boolean deleted;
	private Date lastModifiedDate;
	private Long lastModifiedUser;
	private String name;
	private Set<ProductVersionDTO> productVersions = new HashSet<ProductVersionDTO>();
	private String reportFileLocation;
	private Long vendorId;
	
	public ProductEntityDTO(){}
	public ProductEntityDTO(ProductEntity entity){
		
		this.id = entity.getId();
		this.creationDate = entity.getCreationDate();
		this.deleted = entity.isDeleted();
		this.lastModifiedDate = entity.getLastModifiedDate();
		this.lastModifiedUser = entity.getLastModifiedUser();
		this.name = entity.getName();
		this.reportFileLocation = entity.getReportFileLocation();
		this.vendorId = entity.getVendorId();
		
		Set<ProductVersionEntity> productVersioneEntities = entity.getProductVersions();
		for (ProductVersionEntity pvEntity : productVersioneEntities){
			ProductVersionDTO pvDto = new ProductVersionDTO(pvEntity);
			this.productVersions.add(pvDto);
		}
		
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<ProductVersionDTO> getProductVersions() {
		return productVersions;
	}
	public void setProductVersions(Set<ProductVersionDTO> productVersions) {
		this.productVersions = productVersions;
	}
	public String getReportFileLocation() {
		return reportFileLocation;
	}
	public void setReportFileLocation(String reportFileLocation) {
		this.reportFileLocation = reportFileLocation;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	
}
