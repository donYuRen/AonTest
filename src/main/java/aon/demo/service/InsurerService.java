package aon.demo.service;

import java.util.List;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;

public interface InsurerService {

	List<Insurer> findMatchInsurers(Customer customer);
}
