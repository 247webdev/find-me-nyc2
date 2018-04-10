package com.example.findmenyc2;

import com.example.findmenyc2.models.Result;
import com.example.findmenyc2.repositories.ResultRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ResultsApiApplicationTests {

	@Autowired
	private ResultRepository resultRepository;

	Result secondResult;

	@Before
	public void setUp() {
		resultRepository.deleteAll();

		Result firstResult = new Result(
				"result title 1",
				"111 11th ave, san francisco ca",
				111.12345,
				-111.837432,
				"public hearings 2",
				"public hearings description here 1",
				"date and time goes here 1"
		);

		secondResult = new Result(
				"result title 2",
				"222 22nd ave, san francisco ca",
				122.12345,
				-122.837432,
				"public hearings 2",
				"public hearings description here 2",
				"date and time goes here 2"
		);

		Stream.of(firstResult, secondResult)
				.forEach(user -> {
					resultRepository.save(user);
				});
	}

	@After
	public void tearDown() {
		resultRepository.deleteAll();
	}

	@Test
	public void shouldAllowFullCrudForAResult() throws Exception {

		Result resultNotYetInDb = new Result(
				"result title 3",
				"333 33rd ave, san francisco ca",
				133.12345,
				-133.837432,
				"public hearings 3",
				"public hearings description here 3",
				"date and time goes here 3"
		);

		// Test adding a new result
		given()
				.contentType(JSON)
				.and().body(resultNotYetInDb)
				.when()
				.post("http://localhost:8082")
				.then()
				.statusCode(is(200))
				.and().body(containsString("result title 3"));

		// Test get all Results
		when()
				.get("http://localhost:8082")
				.then()
				.statusCode(is(200))
				.and().body(containsString("result title 1"))
				.and().body(containsString("result title 2"))
				.and().body(containsString("result title 3"));
	}

}
