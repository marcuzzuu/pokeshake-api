package it.marcuzzuu.pokeshakeapi.client.impl;

import it.marcuzzuu.pokeshakeapi.client.ApiClient;
import it.marcuzzuu.pokeshakeapi.client.IPokeApi;
import it.marcuzzuu.pokeshakeapi.client.configuration.ApiClientConfiguration;
import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PokeApi extends ApiClient implements IPokeApi
{
	private final ApiClientConfiguration configuration;

	@Autowired
	public PokeApi(@Qualifier("pokeApiConfiguration") final ApiClientConfiguration configuration)
	{
		this.configuration = configuration;
	}

	@Override
	public PokemonSpecies getSpecies(final String name)
	{
		return null;
	}
}
