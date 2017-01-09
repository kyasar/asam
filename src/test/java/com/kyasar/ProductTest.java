package com.kyasar;

import com.kyasar.model.Product;
import com.kyasar.repository.ProductRepository;
import com.mongodb.gridfs.GridFSInputFile;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ProductRepository repository;

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

		//ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		//System.out.println(result);
		//assertEquals(201, result.getStatusCode().value());

		repository.save(new Product("Urun4", "44", 39.896357, 32.804146));
		repository.save(new Product("Urun5", "55", 39.900439, 32.797623));
		repository.save(new Product("Urun6", "66", 39.889508, 32.791958));
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

		System.out.println("--------------------");
		Circle circle = new Circle(39.899649, 32.805347, 0.1);
		List<Product> products = mongoTemplate.find(new Query(Criteria.where("location").within(circle)), Product.class);
		for (Product p : products) {
			System.out.println(p.getName());
		}

		System.out.println("--------------------");
		Point location = new Point(39.899649, 32.805347);
		NearQuery query = NearQuery.near(location).maxDistance(new Distance(1, Metrics.KILOMETERS));
		List<Product> results = repository.findByLocationWithin(new Polygon(
			new Point(39.887928, 32.794327),
			new Point(39.887638, 32.801811),
			new Point(39.902191, 32.797005),
				new Point(39.890996, 32.772543)
		)); //mongoTemplate.geoNear(query, Product.class);
		//for (GeoResult<Product> p : repository.findByLocationNear(
		//		new Point(39.899649, 32.805347), new Distance(1, Metrics.KILOMETERS))) {
		for (Product p : results) {
			System.out.println(p.getName());
		}
	}
}
