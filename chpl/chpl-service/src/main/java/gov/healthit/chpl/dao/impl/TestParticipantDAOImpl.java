package gov.healthit.chpl.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import gov.healthit.chpl.auth.Util;
import gov.healthit.chpl.dao.EntityCreationException;
import gov.healthit.chpl.dao.EntityRetrievalException;
import gov.healthit.chpl.dao.TestParticipantDAO;
import gov.healthit.chpl.dto.TestParticipantDTO;
import gov.healthit.chpl.entity.TestParticipantEntity;

@Repository("testParticipantDAO")
public class TestParticipantDAOImpl extends BaseDAOImpl implements TestParticipantDAO {
	
	@Override
	public TestParticipantDTO create(TestParticipantDTO dto)
			throws EntityCreationException, EntityRetrievalException {
		
		TestParticipantEntity entity = null;
		try {
			if (dto.getId() != null){
				entity = this.getEntityById(dto.getId());
			}
		} catch (EntityRetrievalException e) {
			throw new EntityCreationException(e);
		}
		
		if (entity != null) {
			throw new EntityCreationException("An entity with this ID already exists.");
		} else {
			entity = new TestParticipantEntity();
			entity.setCreationDate(new Date());
			entity.setDeleted(false);
			entity.setLastModifiedDate(new Date());
			entity.setLastModifiedUser(Util.getCurrentUser().getId());
			entity.setAgeRangeId(dto.getAgeRangeId());
			entity.setAssistiveTechnologyNeeds(dto.getAssistiveTechnologyNeeds());
			entity.setComputerExperienceMonths(dto.getComputerExperienceMonths());
			entity.setEducationTypeId(dto.getEducationTypeId());
			entity.setGender(dto.getGender());
			entity.setOccupation(dto.getOccupation());
			entity.setProductExperienceMonths(dto.getProductExperienceMonths());
			entity.setProfessionalExperienceMonths(dto.getProfessionalExperienceMonths());
			
			create(entity);
			return new TestParticipantDTO(entity);
		}		
	}

	@Override
	public TestParticipantDTO update(TestParticipantDTO dto)
			throws EntityRetrievalException {
		TestParticipantEntity entity = this.getEntityById(dto.getId());
		
		if(entity == null) {
			throw new EntityRetrievalException("Entity with id " + dto.getId() + " does not exist");
		}
		
		entity.setAgeRangeId(dto.getAgeRangeId());
		entity.setAssistiveTechnologyNeeds(dto.getAssistiveTechnologyNeeds());
		entity.setComputerExperienceMonths(dto.getComputerExperienceMonths());
		entity.setEducationTypeId(dto.getEducationTypeId());
		entity.setGender(dto.getGender());
		entity.setOccupation(dto.getOccupation());
		entity.setProductExperienceMonths(dto.getProductExperienceMonths());
		entity.setProfessionalExperienceMonths(dto.getProfessionalExperienceMonths());
		entity.setLastModifiedUser(Util.getCurrentUser().getId());
		entity.setLastModifiedDate(new Date());
		
		entity = update(entity);
		return new TestParticipantDTO(entity);
	}

	@Override
	public void delete(Long id) throws EntityRetrievalException {
		
		TestParticipantEntity toDelete = getEntityById(id);
		
		if(toDelete != null) {
			toDelete.setDeleted(true);
			toDelete.setLastModifiedDate(new Date());
			toDelete.setLastModifiedUser(Util.getCurrentUser().getId());
			update(toDelete);
		}
	}

	@Override
	public TestParticipantDTO getById(Long id)
			throws EntityRetrievalException {
		
		TestParticipantDTO dto = null;
		TestParticipantEntity entity = getEntityById(id);
		
		if (entity != null){
			dto = new TestParticipantDTO(entity);
		}
		return dto;
	}
	
	@Override
	public List<TestParticipantDTO> findAll() {
		
		List<TestParticipantEntity> entities = getAllEntities();
		List<TestParticipantDTO> dtos = new ArrayList<TestParticipantDTO>();
		
		for (TestParticipantEntity entity : entities) {
			TestParticipantDTO dto = new TestParticipantDTO(entity);
			dtos.add(dto);
		}
		return dtos;
		
	}

	private void create(TestParticipantEntity entity) {
		
		entityManager.persist(entity);
		entityManager.flush();
		
	}
	
	private TestParticipantEntity update(TestParticipantEntity entity) {
		
		TestParticipantEntity result = entityManager.merge(entity);	
		entityManager.flush();
		return result;
	}
	
	private List<TestParticipantEntity> getAllEntities() {
		return entityManager.createQuery( "SELECT tpe from TestParticipantEntity tpe "
				+ "LEFT OUTER JOIN FETCH tpe.ageRange "
				+ "LEFT OUTER JOIN FETCH tpe.education "
				+ "where (NOT tpe.deleted = true) ", TestParticipantEntity.class).getResultList();
	}
	
	private TestParticipantEntity getEntityById(Long id) throws EntityRetrievalException {
		
		TestParticipantEntity entity = null;
		
		Query query =  entityManager.createQuery( "SELECT tpe from TestParticipantEntity tpe "
				+ "LEFT OUTER JOIN FETCH tpe.ageRange "
				+ "LEFT OUTER JOIN FETCH tpe.education "
				+ "where (NOT tpe.deleted = true) AND (tpe.id = :entityid)", 
			TestParticipantEntity.class);
		query.setParameter("entityid", id);
		List<TestParticipantEntity> result = query.getResultList();
		
		if (result.size() > 1){
			throw new EntityRetrievalException("Data error. Duplicate test participant id in database.");
		}
		
		if (result.size() > 0){
			entity = result.get(0);
		}
		
		return entity;
	}
}