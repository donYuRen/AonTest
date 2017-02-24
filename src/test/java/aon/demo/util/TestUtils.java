package aon.demo.util;

import java.util.ArrayList;
import java.util.List;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;
import aon.demo.model.Policy;

public class TestUtils {

	
	public static Insurer createInsurer(String insurName, List<Policy> policies) {
		Insurer insur = new Insurer();
		List<Policy> policyArr = new ArrayList<Policy>();
		insur.setName(insurName);
		insur.setPolicies(policies);
		return insur;
		
	}
	public static Policy createPolicy(Long id, String criteria, String strValue, int intValue) {
		Policy policy1 = new Policy();
		policy1.setId(id);
		policy1.setCriteriaIntValue(intValue);
		policy1.setCtrieriaStringValue(strValue);
		policy1.setCriteria(criteria);
		return policy1;
	}

	public static Customer createCustomer(String postcode, String occupation, int turnover) {
		Customer customer = new Customer();
		customer.setPostcode(postcode);
		customer.setTurnover(turnover);
		customer.setOccupation(occupation);
		return customer;
	}
}
