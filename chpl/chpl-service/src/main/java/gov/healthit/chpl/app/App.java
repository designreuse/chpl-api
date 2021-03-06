package gov.healthit.chpl.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import gov.healthit.chpl.app.presenter.CertifiedProduct2014CsvPresenter;
import gov.healthit.chpl.app.presenter.CertifiedProductCsvPresenter;
import gov.healthit.chpl.app.presenter.CertifiedProductXmlPresenter;
import gov.healthit.chpl.dao.CertificationCriterionDAO;
import gov.healthit.chpl.dao.CertifiedProductDAO;
import gov.healthit.chpl.dao.EntityRetrievalException;
import gov.healthit.chpl.domain.CertifiedProductDownloadResponse;
import gov.healthit.chpl.domain.CertifiedProductSearchDetails;
import gov.healthit.chpl.dto.CertificationCriterionDTO;
import gov.healthit.chpl.dto.CertifiedProductDetailsDTO;
import gov.healthit.chpl.manager.CertifiedProductDetailsManager;

@Component("app")
public class App {
    private static final String DEFAULT_PROPERTIES_FILE = "environment.properties";
	private static final Logger logger = LogManager.getLogger(App.class);

	private SimpleDateFormat timestampFormat;
	private CertifiedProductDetailsManager cpdManager;
	private CertifiedProductDAO certifiedProductDAO;
	private CertificationCriterionDAO criteriaDao;
	
    public App() {
    	timestampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    }
    
	public static void main( String[] args ) throws Exception {	
		//read in properties - we need these to set up the data source context
		Properties props = null;
		InputStream in = App.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES_FILE);
		
		if (in == null) {
			props = null;
			throw new FileNotFoundException("Environment Properties File not found in class path.");
		} else {
			props = new Properties();
			props.load(in);
			in.close();
		}

		//set up data source context
		 LocalContext ctx = LocalContextFactory.createLocalContext(props.getProperty("dbDriverClass"));
		 ctx.addDataSource(props.getProperty("dataSourceName"),props.getProperty("dataSourceConnection"), 
				 props.getProperty("dataSourceUsername"), props.getProperty("dataSourcePassword"));
		 
		 //init spring classes
		 AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		 System.out.println(context.getClassLoader());
		 App app = new App();
		 app.setCpdManager((CertifiedProductDetailsManager)context.getBean("certifiedProductDetailsManager"));
		 app.setCertifiedProductDAO((CertifiedProductDAO)context.getBean("certifiedProductDAO"));
		 app.setCriteriaDao((CertificationCriterionDAO)context.getBean("certificationCriterionDAO"));
		 
		 //maps the year to the list of products for that year
		 Map<String, CertifiedProductDownloadResponse> resultMap = 
				 new HashMap<String, CertifiedProductDownloadResponse>();
        
        //write out the file to a different location so as not to 
        //overwrite the existing download file
        List<CertifiedProductDetailsDTO> allCertifiedProducts = app.getCertifiedProductDAO().findAll();
		for(CertifiedProductDetailsDTO currProduct : allCertifiedProducts) {
		//for(int i = 1; i < 10; i++) {
			//CertifiedProductDetailsDTO currProduct = allCertifiedProducts.get(i);
			try {
				
				CertifiedProductSearchDetails product = app.getCpdManager().getCertifiedProductDetails(currProduct.getId());
				String certificationYear = product.getCertificationEdition().get("name").toString();
				certificationYear = certificationYear.trim();
				if(!certificationYear.startsWith("20")) {
					certificationYear = "20" + certificationYear;
				}
				
				if(resultMap.get(certificationYear) == null) {
					CertifiedProductDownloadResponse yearResult = new CertifiedProductDownloadResponse();
					resultMap.put(certificationYear, yearResult);
				}
				((CertifiedProductDownloadResponse)resultMap.get(certificationYear)).getProducts().add(product);
				
			} catch(EntityRetrievalException ex) {
				logger.error("Could not certified product details for certified product " + currProduct.getId());
			}
		}
        
        String downloadFolderPath;
        if (args.length > 0) {
        	downloadFolderPath = args[0];
        } else {
        	downloadFolderPath = props.getProperty("downloadFolderPath");
        }
        File downloadFolder = new File(downloadFolderPath);
        if(!downloadFolder.exists()) {
        	downloadFolder.mkdirs();
        }
        
        Date now = new Date();
        //write out a separate file for each edition
        for(String year : resultMap.keySet()) {
	        //present as xml
	        String xmlFilename = downloadFolder.getAbsolutePath() + File.separator + 
	        		"chpl-" + year + "-" + app.getTimestampFormat().format(now) + ".xml";
	        File xmlFile = new File(xmlFilename);
	        if(!xmlFile.exists()) {
	        	xmlFile.createNewFile();
	        } else {
	        	xmlFile.delete();
	        }
	        CertifiedProductXmlPresenter xmlPresenter = new CertifiedProductXmlPresenter();
	        xmlPresenter.presentAsFile(xmlFile, resultMap.get(year));
	        
	        //present as csv
	        String csvFilename = downloadFolder.getAbsolutePath() + File.separator + 
	        		"chpl-" + year + "-" + app.getTimestampFormat().format(now) + ".csv";
	        File csvFile = new File(csvFilename);
	        if(!csvFile.exists()) {
	        	csvFile.createNewFile();
	        } else {
	        	csvFile.delete();
	        }
	        CertifiedProductCsvPresenter csvPresenter = null;
	        if(year.equals("2014")) {
	        	csvPresenter = new CertifiedProduct2014CsvPresenter();
	        } else {
	        	csvPresenter = new CertifiedProductCsvPresenter();
	        }
	        List<CertificationCriterionDTO> criteria = app.getCriteriaDao().findByCertificationEditionYear(year);
	        csvPresenter.setApplicableCriteria(criteria);
	        csvPresenter.presentAsFile(csvFile, resultMap.get(year));
        }
        
        //write out a file containing all of the products
        CertifiedProductDownloadResponse allResults = new CertifiedProductDownloadResponse();
        for(String year : resultMap.keySet()) { 
        	CertifiedProductDownloadResponse result = resultMap.get(year);
        	allResults.getProducts().addAll(result.getProducts());
        	result.getProducts().clear();
        }
        
        String newFileName = downloadFolder.getAbsolutePath() + File.separator + 
        		"chpl-all-" + app.getTimestampFormat().format(now) + ".xml";
        File newFile = new File(newFileName);
        if(!newFile.exists()) {
        	newFile.createNewFile();
        } else {
        	newFile.delete();
        }
        CertifiedProductXmlPresenter presenter = new CertifiedProductXmlPresenter();
        presenter.presentAsFile(newFile, allResults);
        
        context.close();
	}
	
	public CertifiedProductDAO getCertifiedProductDAO() {
		return certifiedProductDAO;
	}

	public void setCertifiedProductDAO(CertifiedProductDAO certifiedProductDAO) {
		this.certifiedProductDAO = certifiedProductDAO;
	}

	public SimpleDateFormat getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(SimpleDateFormat timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public CertifiedProductDetailsManager getCpdManager() {
		return cpdManager;
	}

	public void setCpdManager(CertifiedProductDetailsManager cpdManager) {
		this.cpdManager = cpdManager;
	}

	public CertificationCriterionDAO getCriteriaDao() {
		return criteriaDao;
	}

	public void setCriteriaDao(CertificationCriterionDAO criteriaDao) {
		this.criteriaDao = criteriaDao;
	}
}
