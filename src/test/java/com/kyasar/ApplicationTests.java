package com.kyasar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void getProducts()
	{
		final String uri = "http://localhost:8080/product";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		System.out.println(result);
	}
}
