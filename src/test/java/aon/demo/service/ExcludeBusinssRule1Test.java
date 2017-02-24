package aon.demo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import aon.demo.model.Customer;
import aon.demo.model.Policy;
import aon.demo.util.TestUtils;
@RunWith(SpringRunner.class)
public class ExcludeBusinssRule1Test {

	private ExcludeBusinessRule1 testObject = new ExcludeBusinessRule1();
	@Test
	public void testCheck() {
		
		List<Policy> policies = new ArrayList<Policy>();
		policies.add(TestUtils.createPolicy(1l, "postcode","2000,2001", -1));
		policies.add(TestUtils.createPolicy(5l, "occupation","Butcher", -1));
		Customer customer = TestUtils.createCustomer("2002", "Doctoe", 200000);
		assertFalse(testObject.check(TestUtils.createInsurer("insur1", policies), customer));
		assertFalse(testObject.check(TestUtils.createInsurer("insur1", null), customer));
		assertFalse(testObject.check(TestUtils.createInsurer("insur1", policies), TestUtils.createCustomer("2001", "Doctor", 200000)));
		assertTrue(testObject.check(TestUtils.createInsurer("insur1", policies), TestUtils.createCustomer("2001", "Butcher", 200000)));
		
	}




}
