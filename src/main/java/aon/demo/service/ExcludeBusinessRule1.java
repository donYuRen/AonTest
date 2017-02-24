package aon.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;
import aon.demo.model.Policy;

@Component
public class ExcludeBusinessRule1 implements BusinessRule {
	public static final Logger logger = LoggerFactory.getLogger(ExcludeBusinessRule1.class);

	private static final String POSTCODE = "postcode";
	private static final String OCCUPATION = "occupation";
    private boolean postcodeCheck = false;
    private boolean occupationCheck = false;

	@Override
	public boolean check(Insurer insurer, Customer customer) {
	    postcodeCheck = false;
	    occupationCheck = false;
	    boolean postcodePolicy = false;
	    boolean occupationPolicy = false;
		if (insurer != null) {
			List<Policy> policies = insurer.getPolicies();
			if (policies != null) {
				for (Policy policy : policies) {
					if (!postcodeCheck) {
						postcodePolicy = unmatchPostcodePolicy(policy, customer);
						continue;
					}
					if (!occupationCheck) {
						occupationPolicy = unmatchOccupationPolicy(policy, customer);
					}
				}
			}
		}
		return postcodePolicy && occupationPolicy;
	}
	private boolean unmatchPostcodePolicy(Policy policy, Customer customer) {
		String[] allExcludPostcode = null;
		String postcodeValue = policy.getCtrieriaStringValue();
		
		if (POSTCODE.equals(policy.getCriteria())) {
			postcodeCheck = true;
			if (postcodeValue != null) {
				allExcludPostcode = postcodeValue.split(",");
				for (String postcode : allExcludPostcode) {
					if (customer.getPostcode().trim().toLowerCase().equals(postcode.toLowerCase().trim())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean unmatchOccupationPolicy(Policy policy, Customer customer){
		String[] allExcludeOccupation = null;
		String occuValue = policy.getCtrieriaStringValue();
		if (OCCUPATION.equals(policy.getCriteria())) {
			occupationCheck =true;
			if (occuValue != null) {
				allExcludeOccupation = occuValue.split(",");
				for (String occu : allExcludeOccupation) {
					if (customer.getOccupation().toLowerCase().trim().equals(occu.toLowerCase().trim())) {
						return true;
					}
				}
			}			
		}
		return false;

	}

}
