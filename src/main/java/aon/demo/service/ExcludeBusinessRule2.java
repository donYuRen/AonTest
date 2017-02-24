package aon.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;
import aon.demo.model.Policy;

@Component
public class ExcludeBusinessRule2 implements BusinessRule {

	public static final Logger logger = LoggerFactory.getLogger(ExcludeBusinessRule2.class);


	private static final String TURNOVER = "turnover";
	private boolean turnoverCheck;

	@Override
	public boolean check(Insurer insurer, Customer customer) {
		turnoverCheck =false;
		boolean turnoverPolicy = false;
		if (insurer != null) {
			List<Policy> policies = insurer.getPolicies();
			if (policies != null) {
				for (Policy policy : policies) {
					if (turnoverCheck) {
						break;
					}
					turnoverPolicy = unmatchTurnOverPolicy(policy, customer);
				}
			}
		}
		return turnoverPolicy;
	}

	private boolean unmatchTurnOverPolicy(Policy policy, Customer customer){
		if (TURNOVER.equals(policy.getCriteria())) {
			turnoverCheck =true;
			return customer.getTurnover() < policy.getCriteriaIntValue();
		}
		return false;

	}
}
