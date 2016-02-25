package gov.healthit.chpl.certifiedProduct.upload;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import gov.healthit.chpl.domain.CQMCriterion;
import gov.healthit.chpl.entity.PendingCertifiedProductEntity;

public interface CertifiedProductUploadHandler {
	public PendingCertifiedProductEntity handle();
	public List<CQMCriterion> getApplicableCqmCriterion(List<CQMCriterion> allCqms);
	public List<CSVRecord> getRecord();
	public void setRecord(List<CSVRecord> record);
	public CSVRecord getHeading();
	public void setHeading(CSVRecord heading);
	public int getLastDataIndex();
	public void setLastDataIndex(int lastDataIndex);
}
