package it.marcuzzuu.pokeshakeapi.integration;

import it.marcuzzuu.pokeshakeapi.controller.PokemonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.util.Assert;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonControllerTest
{
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private PokemonController controller;

	@Test
	void getDescriptionWithValidNameShouldReturnShakespeareanDescriptionAnd200()
	{
		Assert.notNull(this.controller, "REST Controller is null");
	}

	@Test
	void getDescriptionWithInValidNameShouldReturnGivenNameAnd404()
	{

	}

	@Test
	void getDescriptionWithNoNameShouldReturn404()
	{

	}

	@Test
	void getDescriptionWithValidNameAndErrorsOccurredShouldReturn500()
	{

	}

	@Test
	void getDescriptionWithValidNameAndTooManyRequestsOccurredShouldReturn429()
	{

	}
}
