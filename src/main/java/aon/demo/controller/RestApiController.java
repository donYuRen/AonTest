package aon.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import aon.demo.model.Customer;
import aon.demo.model.Insurer;
import aon.demo.service.InsurerService;


@RestController
@RequestMapping("/api")
public class RestApiController {
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	InsurerService insurerService;

	@RequestMapping(value = "/search/", method = RequestMethod.POST)
	public ResponseEntity<List<Insurer>> createUser(@RequestBody Customer customer) {
		List<Insurer> insurers = insurerService.findMatchInsurers(customer);		
		return new ResponseEntity<List<Insurer>>(insurers, HttpStatus.OK);
	}
}
