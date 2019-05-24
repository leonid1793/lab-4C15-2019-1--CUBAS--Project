package com.tecsup.petclinic.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class OwnerServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(PetServiceTest.class);

	@Autowired
	private OwnerService ownerService;
	
	@Test
	public void testFindByFirstName() {
		String OWNER_FIRST_NAME = "Maria";
		Owner owner; 	
		List<Owner> owners = ownerService.findByFirstName(OWNER_FIRST_NAME);
		owner = owners.get(0);
		
		Assert.assertEquals(OWNER_FIRST_NAME, owner.getFirstName());
		logger.info("Propietario de nombre '" + OWNER_FIRST_NAME + "' encotrado.");
	}
	
	@Test
	public void testFindByLastName() {
		String OWNER_LAST_NAME = "Black";
		Owner owner;
		List<Owner> owners = ownerService.findByLastName(OWNER_LAST_NAME);
		owner = owners.get(0);
		
		Assert.assertEquals(OWNER_LAST_NAME, owner.getLastName());
		logger.info("Apellido del Propietario '" + OWNER_LAST_NAME + "Encontrado");
	}
	
	@Test
	public void testFindByAddress() {
		String OWNER_CITY = "Monona";
		Owner owner;
		List<Owner> owners = ownerService.findByCity(OWNER_CITY);
		owner = owners.get(0);
		
		Assert.assertEquals(OWNER_CITY, owner.getCity());
		logger.info("Se encontro por ciudad '" + OWNER_CITY + "");
	}
	
	//@Test
	public void testDelete() throws OwnerNotFoundException {
		Long OWNER_ID = 10L;//
		
		ownerService.delete(OWNER_ID);
		
		try {
			Owner owner = ownerService.findById(OWNER_ID);
		}catch (Exception e) {
			logger.info("Owner with id: "+ OWNER_ID + " has deleted.");
		}
	}
	
	@Test
	public void testCreateAndFind() {
		String OWNER_FIRST_NAME = "Leonid";
		String OWNER_LAST_NAME = "Cubas";
		String OWNER_CITY = "Lima";
		
		Owner simpleOwner = new Owner(OWNER_FIRST_NAME, OWNER_LAST_NAME, OWNER_CITY);
		
		Owner ownerCreated = ownerService.create(simpleOwner);
		
		try {
			Owner ownerCreatedFound = ownerService.findById(ownerCreated.getId());
			logger.info("Owner with id: "+ ownerCreatedFound.getId() + " has created.");
		}catch (OwnerNotFoundException e) {
			logger.info("Owner had no created.");
		}
		
		Iterable<Owner> ownersI = ownerService.findAll();
		
		while(ownersI.iterator().hasNext()) {
			try {
				Owner ownerInListFound = ownerService.findById(ownerCreated.getId());
				logger.info("Owner with id: "+ ownerInListFound.getId() + " exists.");
				break;
			}catch (OwnerNotFoundException e) {
				logger.info("Owner doesn´t exists.");
			}
		}
	}

}
