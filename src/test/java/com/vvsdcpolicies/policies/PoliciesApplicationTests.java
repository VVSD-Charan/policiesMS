package com.vvsdcpolicies.policies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.vvsdcpolicies.policies.dto.PoliciesDTO;
import com.vvsdcpolicies.policies.entity.Policies;
import com.vvsdcpolicies.policies.repository.PoliciesRepository;
import com.vvsdcpolicies.policies.service.PoliciesServiceImpl;

@SpringBootTest
class PoliciesApplicationTests {
	
	@Mock
	private PoliciesRepository policiesRepository;
	
	@InjectMocks
	private PoliciesServiceImpl policiesService;

	@Test
	public void addPolicyValidTest()
	{
		PoliciesDTO policiesDTO = new PoliciesDTO();
		Policies policies = new Policies();
		
		Mockito.when(policiesRepository.save(policies)).thenReturn(policies);
		Assertions.assertDoesNotThrow(()-> policiesService.addPolicy(policiesDTO));
	}
}
