package gov.healthit.chpl.domain;

import java.io.Serializable;
import java.util.Set;

public class PopulateSearchOptions implements Serializable {
	private static final long serialVersionUID = 448737963706046739L;
	private Set<KeyValueModel> productClassifications;
	private Set<KeyValueModel> editions;
	private Set<KeyValueModel> certificationStatuses;
	private Set<KeyValueModel> practiceTypeNames;
	private Set<KeyValueModelStatuses> productNames;
	private Set<KeyValueModelStatuses> developerNames;
	private Set<KeyValueModel> certBodyNames;
	private Set<DescriptiveModel> certificationCriterionNumbers;
	private Set<DescriptiveModel> cqmCriterionNumbers;
	public Set<KeyValueModel> getProductClassifications() {
		return productClassifications;
	}
	public void setProductClassifications(Set<KeyValueModel> productClassifications) {
		this.productClassifications = productClassifications;
	}
	public Set<KeyValueModel> getEditions() {
		return editions;
	}
	public void setEditions(Set<KeyValueModel> editions) {
		this.editions = editions;
	}
	public Set<KeyValueModel> getPracticeTypeNames() {
		return practiceTypeNames;
	}
	public void setPracticeTypeNames(Set<KeyValueModel> practiceTypeNames) {
		this.practiceTypeNames = practiceTypeNames;
	}
	public Set<KeyValueModelStatuses> getProductNames() {
		return productNames;
	}
	public void setProductNames(Set<KeyValueModelStatuses> productNames) {
		this.productNames = productNames;
	}
	public Set<KeyValueModelStatuses> getDeveloperNames() {
		return developerNames;
	}
	public void setDeveloperNames(Set<KeyValueModelStatuses> developerNames) {
		this.developerNames = developerNames;
	}
	public Set<KeyValueModel> getCertBodyNames() {
		return certBodyNames;
	}
	public void setCertBodyNames(Set<KeyValueModel> certBodyNames) {
		this.certBodyNames = certBodyNames;
	}
	public Set<DescriptiveModel> getCertificationCriterionNumbers() {
		return certificationCriterionNumbers;
	}
	public void setCertificationCriterionNumbers(Set<DescriptiveModel> certificationCriterionNumbers) {
		this.certificationCriterionNumbers = certificationCriterionNumbers;
	}
	public Set<DescriptiveModel> getCqmCriterionNumbers() {
		return cqmCriterionNumbers;
	}
	public void setCqmCriterionNumbers(Set<DescriptiveModel> cqmCriterionNumbers) {
		this.cqmCriterionNumbers = cqmCriterionNumbers;
	}
	public Set<KeyValueModel> getCertificationStatuses() {
		return certificationStatuses;
	}
	public void setCertificationStatuses(Set<KeyValueModel> certificationStatuses) {
		this.certificationStatuses = certificationStatuses;
	}
	

}
