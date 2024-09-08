package com.vvsdcpolicies.policies.service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvsdcpolicies.policies.dto.PoliciesDTO;
import com.vvsdcpolicies.policies.entity.Policies;
import com.vvsdcpolicies.policies.exception.VvsdcException;
import com.vvsdcpolicies.policies.repository.PoliciesRepository;

@Service
@Transactional
public class PoliciesServiceImpl implements PoliciesService {
	
	ModelMapper modelMapper = new ModelMapper();
	private PoliciesRepository policiesRepository; 
	

	public PoliciesServiceImpl(PoliciesRepository policiesRepository) {
		super();
		this.policiesRepository = policiesRepository;
	}


	@Override
	public void addPolicy(PoliciesDTO policiesDTO) throws VvsdcException {
		Policies policy = modelMapper.map(policiesDTO,Policies.class);
		policiesRepository.save(policy);
	}

}
