package it.marcuzzuu.pokeshakeapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.marcuzzuu.pokeshakeapi.controller.PokemonController;
import it.marcuzzuu.pokeshakeapi.model.ErrorResponse;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PokemonControllerTest
{
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private PokemonController controller;

	@Test
	void getDescriptionWithValidNameShouldReturnShakespeareanDescriptionAnd200()
	{
		final String name = "charizard";
		final ResponseEntity<PokemonDescription> response = this.restTemplate.getForEntity("/pokemon/{name}", PokemonDescription.class, name);
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK), "Status code is not 200");
		Assert.isTrue(response.getBody() != null, "Response has a null body");
		Assert.isTrue(response.getBody().getName() != null && response.getBody().getName().equals(name), "Received response for different pokemon");
		Assert.isTrue(response.getBody().getDescription() != null && !response.getBody().getDescription().isEmpty(), "Description is empty");
	}

	@Test
	void getDescriptionWithInValidNameShouldReturnGivenNameAnd404()
	{
		final String name = "!charizard!";
		final ResponseEntity<PokemonDescription> response = this.restTemplate.getForEntity("/pokemon/{name}", PokemonDescription.class, name);
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND), "Status code is not 404");
		Assert.isTrue(response.getBody() != null, "Response has a null body");
		Assert.isTrue(response.getBody().getName() != null && response.getBody().getName().equals(name), "Received response for different pokemon");
		Assert.isTrue(response.getBody().getDescription() != null && response.getBody().getDescription().isEmpty(), "Description is not empty");
	}

	@Test
	void getDescriptionWithNoNameShouldReturnEmptyBodyAnd404()
	{
		final String name = "";
		final ResponseEntity<PokemonDescription> response = this.restTemplate.getForEntity("/pokemon/{name}", PokemonDescription.class, name);
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND), "Status code is not 404");
		Assert.isTrue(response.getBody() != null && response.getBody().getName() == null && response.getBody().getDescription() == null, "Body is not fully empty");
	}

	@Test
	void getDescriptionWithValidNameAndTooManyRequestsOccurredShouldReturn429()
	{
		final String[] names = {"charizard", "bulbasaur", "ivysaur", "giratina", "mewtwo", "pikachu"};
		for (final String name : names)
		{
			final ResponseEntity<?> response = this.restTemplate.getForEntity("/pokemon/{name}", Object.class, name);
			if (response.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS))
			{
				final ErrorResponse errorResponse = this.objectMapper.convertValue(response.getBody(), ErrorResponse.class);
				Assert.isTrue(errorResponse != null, "No error response received");
				Assert.isTrue(errorResponse.getStatusCode() == 429 && errorResponse.getError().toLowerCase().contains("too many requests"), "Different error description received");
				break;
			}
		}
	}
}
