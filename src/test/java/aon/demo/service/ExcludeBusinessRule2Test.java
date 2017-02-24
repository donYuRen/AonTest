package aon.demo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import aon.demo.model.Customer;
import aon.demo.model.Policy;
import aon.demo.util.TestUtils;

public class ExcludeBusinessRule2Test {
	private ExcludeBusinessRule2 testObject = new ExcludeBusinessRule2();
	@Test
	public void testCheck() {
		
		List<Policy> policies = new ArrayList<Policy>();
		policies.add(TestUtils.createPolicy(1l, "turnover",null, 100000));
		Customer customer = TestUtils.createCustomer("2002", "Doctoe", 200000);
		assertFalse(testObject.check(TestUtils.createInsurer("insur1", policies), customer));
		assertFalse(testObject.check(TestUtils.createInsurer("insur1", null), customer));

		List<Policy> policies1 = new ArrayList<Policy>();
		policies1.add(TestUtils.createPolicy(5l, "turnover",null, 250000));
		
		assertTrue(testObject.check(TestUtils.createInsurer("insur1", policies1), TestUtils.createCustomer("2001", "Butcher", 200000)));

		
	}




}
