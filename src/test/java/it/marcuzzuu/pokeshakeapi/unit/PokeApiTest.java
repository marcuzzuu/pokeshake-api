package it.marcuzzuu.pokeshakeapi.unit;

import it.marcuzzuu.pokeshakeapi.client.configuration.PokeApiConfiguration;
import it.marcuzzuu.pokeshakeapi.client.impl.PokeApi;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.FlavorTextEntry;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class PokeApiTest
{
	private final PokeApi pokeApi;
	private final RestTemplate restTemplate;

	@Autowired
	PokeApiTest(final PokeApiConfiguration configuration)
	{
		this.restTemplate = Mockito.mock(RestTemplate.class);
		this.pokeApi = new PokeApi(configuration, this.restTemplate);
	}

	@Test
	void getPokemonSpeciesWithValidNameAndSuccessfulResponseShouldReturnNotEmptyPokemonSpecies()
	{
		final String name = "charizard";
		final PokemonSpecies species = new PokemonSpecies();
		species.setFlavorTextEntries(Collections.singletonList(FlavorTextEntry.builder().flavorText("Charizard is a big pokemon!").build()));

		given(this.restTemplate.exchange(this.pokeApi.buildSpeciesURI(name), HttpMethod.GET, new HttpEntity<>(PokeApi.DEFAULT_HEADERS), PokemonSpecies.class)).willReturn(new ResponseEntity<>(species, HttpStatus.OK));
		final Optional<PokemonSpecies> returnedValue = this.pokeApi.getSpecies(name);

		Assert.isTrue(returnedValue.isPresent() && returnedValue.get().getFlavorTextEntries().stream().anyMatch(fte -> fte.getFlavorText().equals("Charizard is a big pokemon!")), "Returned species is different from the one expected!");
	}

	@Test
	void getPokemonSpeciesWithInvalidNameAndUnsuccessfulResponseShouldReturnEmpty()
	{
		final String name = "!charizard!";

		given(this.restTemplate.exchange(this.pokeApi.buildSpeciesURI(name), HttpMethod.GET, new HttpEntity<>(PokeApi.DEFAULT_HEADERS), PokemonSpecies.class)).willReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		final Optional<PokemonSpecies> returnedValue = this.pokeApi.getSpecies(name);

		Assert.isTrue(!returnedValue.isPresent(), "Species returned when it should be empty!");
	}

	@Test()
	void getPokemonSpeciesWithValidNameAndTooManyRequestsShouldThrowException()
	{
		final String name = "charizard";
		boolean expectedExceptionThrown = false;

		given(this.restTemplate.exchange(this.pokeApi.buildSpeciesURI(name), HttpMethod.GET, new HttpEntity<>(PokeApi.DEFAULT_HEADERS), PokemonSpecies.class)).willThrow(new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS));
		try
		{
			this.pokeApi.getSpecies(name);
		}
		catch (HttpClientErrorException ex)
		{
			if (ex.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS))
			{
				expectedExceptionThrown = true;
			}
		}

		Assert.isTrue(expectedExceptionThrown, "Exception is never been thrown!");
	}
}
