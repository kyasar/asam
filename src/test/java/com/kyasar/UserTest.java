package com.kyasar;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Value("${mapis.api.url}")
	private String apiURL;

	@Test
	public void t0_cleanUp()
	{
		for (String collectionName : mongoTemplate.getDb().getCollectionNames()) {
			if (!collectionName.startsWith("system")) {
				System.out.println("Droppping table: " + collectionName);
				mongoTemplate.dropCollection(collectionName);
			}
		}
	}

	@Test
	public void t1_insertProducts()
	{
		final String uri = apiURL + "/product";

		RestTemplate restTemplate = new RestTemplate();
		JSONObject body = new JSONObject();
		body.put("name", "Urun1");
		body.put("barcode", "11111");
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body.toString(), headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

		System.out.println(result);
		assertEquals(201, result.getStatusCode().value());
	}

	@Test
	public void t2_getProducts()
	{
		final String uri = apiURL + "/product";

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		System.out.println(result);
		assertEquals(200, result.getStatusCode().value());
	}
}
