package com.kyasar;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
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

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Value("${mapis.api.url}")
	private String apiURL;

	@Value("${mapis.client.id}")
	private String apiClientId;

	@Value("${mapis.client.secret}")
	private String apiClientSecret;

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
	public void t1_insertUser()
	{
		final String uri = apiURL + "/users";

		RestTemplate restTemplate = new RestTemplate();
		JSONObject body = new JSONObject();
		body.put("name", "kadir");
		body.put("surname", "yasar");
		body.put("username", "kyasar");
		body.put("password", "123");
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(body.toString(), headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

		System.out.println(result);
		assertEquals(201, result.getStatusCode().value());
	}

	@Test
	public void t2_authUser()
	{
		StringBuilder uri = new StringBuilder(apiURL + "/oauth/token?");
		uri.append("grant_type=");
		uri.append("password");
		uri.append("&");
		uri.append("username=");
		uri.append("kyasar");
		uri.append("&");
		uri.append("password=");
		uri.append("123");

		RestTemplate restTemplate = new RestTemplate();

		String plainCreds = apiClientId + ":" + apiClientSecret;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.POST, request, String.class);

		System.out.println(response);
		//assertEquals(201, result.getStatusCode().value());
	}

}
