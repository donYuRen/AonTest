package aon.demo.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import aon.demo.model.Insurer;
import aon.demo.model.Policy;
import aon.demo.repositories.InsurerRepository;
import aon.demo.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsurerServiceImplTest {

	@MockBean
	private InsurerRepository insurerRepositoryMock;

	@Autowired
	private InsurerService insurerService;

	@Test
	public void test() {

		BDDMockito.given(insurerRepositoryMock.findAll()).willReturn(
				createAllInsurers());
		List<Insurer> testResult1 = insurerService.findMatchInsurers(TestUtils
				.createCustomer("2000", "Builder", 600000));
		assertTrue(testResult1.size() == 4);
		assertThat(
				testResult1,
				contains(hasProperty("name", is("insur1")),
						 hasProperty("name", is("insur2")),
						 hasProperty("name", is("insur3")),
						 hasProperty("name", is("insur4"))));

		List<Insurer> testResult2 = insurerService.findMatchInsurers(TestUtils
				.createCustomer("2000", "Builder", 100000));
		assertTrue(testResult2.size() == 2);
		assertThat(
				testResult2,
				contains(hasProperty("name", is("insur1")),
						 hasProperty("name", is("insur2"))));

		List<Insurer> testResult3 = insurerService.findMatchInsurers(TestUtils
				.createCustomer("2000", "Plumber", 600000));
		assertTrue(testResult3.size() == 3);
		assertThat(
				testResult3,
				contains(hasProperty("name", is("insur1")),
						 hasProperty("name", is("insur2")),
						 hasProperty("name", is("insur4"))));

		List<Insurer> testResult4 = insurerService.findMatchInsurers(TestUtils
				.createCustomer("2001", "Butcher", 300000));
		assertTrue(testResult4.size() == 2);
		assertThat(
				testResult4,
				contains(hasProperty("name", is("insur1")),
						hasProperty("name", is("insur3"))));

	}

	private List<Insurer> createAllInsurers() {
		List<Insurer> insurers = new ArrayList<Insurer>();
		insurers.add(TestUtils.createInsurer("insur1", null));

		List<Policy> policies = new ArrayList<Policy>();
		policies.add(TestUtils.createPolicy(1l, "postcode", "2000,2001", -1));
		policies.add(TestUtils.createPolicy(5l, "occupation", "Butcher", -1));
		insurers.add(TestUtils.createInsurer("insur2", policies));

		List<Policy> policies3 = new ArrayList<Policy>();
		policies3.add(TestUtils.createPolicy(8l, "postcode", "2000", -1));
		policies3.add(TestUtils.createPolicy(6l, "turnover", null, 200000));
		policies3.add(TestUtils.createPolicy(4l, "occupation",
				"Butcher,Plumber", -1));
		insurers.add(TestUtils.createInsurer("insur3", policies3));

		List<Policy> policies4 = new ArrayList<Policy>();
		policies4.add(TestUtils.createPolicy(7l, "turnover", null, 400000));
		insurers.add(TestUtils.createInsurer("insur4", policies4));
		return insurers;

	}

}
