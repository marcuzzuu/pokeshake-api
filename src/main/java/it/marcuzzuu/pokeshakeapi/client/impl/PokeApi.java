package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ApiClient;
import it.marcuzzuu.pokeshakeapi.client.IPokeApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.PokeApiConfiguration;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Slf4j
@Component
public class PokeApi extends ApiClient implements IPokeApi
{
	public static final String DEFAULT_LANGUAGE = "en";
	private final PokeApiConfiguration configuration;

	@Autowired
	public PokeApi(final PokeApiConfiguration configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public Optional<PokemonSpecies> getSpecies(final String name)
	{
		try
		{
			final ResponseEntity<PokemonSpecies> response = this.restTemplate.exchange(buildURI(name), HttpMethod.GET, new HttpEntity<>(DEFAULT_HEADERS), PokemonSpecies.class);
			return Optional.ofNullable(response != null && response.getStatusCode().equals(HttpStatus.OK) ? response.getBody() : null);
		}catch (HttpClientErrorException ex)
		{
			log.warn("Got problems finding pokemon '{}': {}", name, ex.getResponseBodyAsString());
		}
		return Optional.empty();
	}

	private String buildURI(final String pokemonName)
	{
		return String.format("%s/%s/%s", this.configuration.getEndpoint(), this.configuration.getSpeciesResource(), pokemonName);
	}
}
