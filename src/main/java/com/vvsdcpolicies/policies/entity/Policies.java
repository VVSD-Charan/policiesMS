package com.vvsdcpolicies.policies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="policies")
public class Policies {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer policyId;
	
	String policyName;
	
	@Column(name="policy_price")
	Integer policyPrice;
}
