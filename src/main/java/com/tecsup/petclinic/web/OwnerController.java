package com.tecsup.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.service.OwnerService;

@RestController
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/owners")
	public Iterable<Owner> getOwners() {
		return ownerService.findAll();
	}
	
	@PostMapping("/owners")
	@ResponseStatus(HttpStatus.CREATED)
	Owner create(@RequestBody Owner owner) {
		return ownerService.create(owner);
	}
	
	@PutMapping("/owners/{id}")
	Owner update(@RequestBody Owner owner) {
		return null;
	}
}
