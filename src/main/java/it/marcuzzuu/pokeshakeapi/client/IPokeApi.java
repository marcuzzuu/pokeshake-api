package it.marcuzzuu.pokeshakeapi.client;

import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;

public interface IPokeApi
{
	PokemonSpecies getSpecies(final String name);
}
