package com.techie.microservices.product;

import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.repository.ProductRepository;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.testcontainers.containers.MongoDBContainer;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@Autowired
	private ProductRepository productRepository;

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@AfterEach
	void cleanUp(){
		productRepository.deleteAll();
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
        String requestBody = """
				     {
				       "id": "sams_3",
				       "name": "samsung",
				       "description": "portale !",
				       "price": 130
				     }
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product/")
				.then()
				.statusCode(201)
				.body("name",Matchers.equalTo("samsung"))
				.body("price",Matchers.equalTo(130))
				.body("description",Matchers.equalTo("portale !"));

		List<Product> products = productRepository.findAll();

		assertEquals(1, products.size());
		Product savedProduct = products.get(0);

		assertEquals("portale !", savedProduct.getDescription());
		assertEquals(BigDecimal.valueOf(130), savedProduct.getPrice());
	}

}
