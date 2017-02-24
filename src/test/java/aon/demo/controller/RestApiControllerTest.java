package aon.demo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import aon.demo.model.Insurer;
import aon.demo.model.Policy;
import aon.demo.service.InsurerService;
import aon.demo.util.TestUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApiController.class)
public class RestApiControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	@Autowired
	private MockMvc mvc;

	@MockBean
	InsurerService insurerService;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);


    }
	@Test
	public void contextLoads() throws Exception {
		given(
				this.insurerService.findMatchInsurers(TestUtils.createCustomer(
						"2000", "Builder", 100000))).willReturn(
				createMockInsurers());
		this.mvc.perform(
				post("/api/search/").contentType(contentType).content(
						json(TestUtils.createCustomer(
								"2000", "Builder", 100000)))
						.accept(contentType)).andExpect(
				status().isOk());

	}

	private List<Insurer> createMockInsurers() {
		List<Insurer> insurers = new ArrayList<Insurer>();
		insurers.add(TestUtils.createInsurer("insur1", null));

		List<Policy> policies = new ArrayList<Policy>();
		policies.add(TestUtils.createPolicy(1l, "postcode", "2000,2001", -1));
		policies.add(TestUtils.createPolicy(5l, "occupation", "Butcher", -1));
		insurers.add(TestUtils.createInsurer("insur2", policies));
		return insurers;
	}
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
