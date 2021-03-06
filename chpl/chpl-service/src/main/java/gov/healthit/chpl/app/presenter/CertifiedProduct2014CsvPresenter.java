package gov.healthit.chpl.app.presenter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gov.healthit.chpl.domain.CertifiedProductSearchDetails;
import gov.healthit.chpl.dto.CertificationCriterionDTO;

public class CertifiedProduct2014CsvPresenter extends CertifiedProductCsvPresenter {
	@Override
	protected List<String> generateHeaderValues() {
		List<String> result = new ArrayList<String>();
		result.add("Certification Edition");
		result.add("CHPL ID");
		result.add("ONC-ACB Certification ID");
		result.add("Certification Date");
		result.add("Certification Status");
		result.add("ACB Name");
		result.add("Previous ACB Name");
		result.add("Developer Name");
		result.add("Product Name");
		result.add("Version");
		result.add("Practice Type");
		result.add("Total CAPS");
		result.add("Open CAPS");
		if(getApplicableCriteria() != null) {
			for(CertificationCriterionDTO criteria : getApplicableCriteria()) {
				result.add(criteria.getNumber());
			}
		}
		return result;
	}
	
	@Override
	protected List<String> generateRowValue(CertifiedProductSearchDetails data) {
		List<String> result = new ArrayList<String>();
		result.add(data.getCertificationEdition().get("name").toString());
		result.add(data.getChplProductNumber());
		result.add(data.getAcbCertificationId());
		LocalDateTime date = LocalDateTime.ofInstant(
				Instant.ofEpochMilli(data.getCertificationDate()), 
			    ZoneId.systemDefault());
		result.add(DateTimeFormatter.ISO_LOCAL_DATE.format(date));
		result.add(data.getCertificationStatus().get("name").toString());
		result.add(data.getCertifyingBody().get("name").toString());
		result.add(data.getOtherAcb());
		result.add(data.getDeveloper().getName());
		result.add(data.getProduct().getName());
		result.add(data.getVersion().getVersion());
		result.add(data.getPracticeType().get("name").toString());
		result.add(data.getCountCorrectiveActionPlans().toString());
		result.add(data.getCountCurrentCorrectiveActionPlans().toString());
		List<String> criteria = generateCriteriaValues(data);
		result.addAll(criteria);
		return result;
	}
}
