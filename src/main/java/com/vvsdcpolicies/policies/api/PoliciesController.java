package com.vvsdcpolicies.policies.api;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vvsdcpolicies.policies.dto.PoliciesDTO;
import com.vvsdcpolicies.policies.exception.VvsdcException;
import com.vvsdcpolicies.policies.service.PoliciesService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping(value = "/api/policies/")
public class PoliciesController {
	
	private PoliciesService policiesService;
	private Environment environment;
	
	
	public PoliciesController(PoliciesService policiesService,Environment environment) {
		super();
		this.policiesService = policiesService;
		this.environment = environment;
	}

	@PostMapping(value="register")
	public ResponseEntity<String> addPolicies(@Valid @RequestBody PoliciesDTO policiesDTO) throws VvsdcException
	{
		policiesService.addPolicy(policiesDTO);
		return new ResponseEntity<>(environment.getProperty("API.ADD_POLICY_SUCCESS"),HttpStatus.CREATED);
		
	}
}
