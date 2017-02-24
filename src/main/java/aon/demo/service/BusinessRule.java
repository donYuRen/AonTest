package aon.demo.service;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;

public interface BusinessRule {

	boolean check(Insurer insurer, Customer customer);
}
