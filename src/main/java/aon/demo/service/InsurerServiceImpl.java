package aon.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;
import aon.demo.repositories.InsurerRepository;

@Service("insurerService")
@Transactional
public class InsurerServiceImpl implements InsurerService {

	public static final Logger logger = LoggerFactory.getLogger(InsurerServiceImpl.class);

	private List<BusinessRule> businessRules;

	private List<Insurer> insurers;

	@Autowired
	private InsurerRepository insurerRepository;

	@Autowired
	public InsurerServiceImpl(List<BusinessRule> businessRules) {
		this.businessRules = businessRules;
	}

	@Override
	public List<Insurer> findMatchInsurers(Customer customer) {
		insurers = insurerRepository.findAll();
		List<Insurer> resultInsurers = new ArrayList<Insurer>(insurers);
		for (Insurer insurer : insurers) {
			for (BusinessRule businessRule : businessRules) {
				if (businessRule.check(insurer, customer)
						&& resultInsurers.contains(insurer)) {
					resultInsurers.remove(insurer);
				}
			}
		}
		return resultInsurers;
	}

}
