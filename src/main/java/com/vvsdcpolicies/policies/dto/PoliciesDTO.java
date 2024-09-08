package com.vvsdcpolicies.policies.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PoliciesDTO {
	Integer policyId;
	
	@NotNull(message="{POLICY_NAME_NULL}")
	@Pattern(regexp = "[A-Za-z ]+" ,message="{POLICY_PATTERN_INVALID}")
	String policyName;
	
	@NotNull(message="{POLICY_PRICE_NULL}")
	@Min(value=0,message="{POLICY_PRICE_NEGATIVE}")
	Integer policyPrice;
}
