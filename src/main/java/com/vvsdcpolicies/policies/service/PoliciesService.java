package com.vvsdcpolicies.policies.service;
import com.vvsdcpolicies.policies.dto.PoliciesDTO;
import com.vvsdcpolicies.policies.exception.VvsdcException;

public interface PoliciesService {
	public void addPolicy(PoliciesDTO policiesDTO) throws VvsdcException;
}
