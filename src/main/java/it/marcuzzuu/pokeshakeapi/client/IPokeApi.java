package it.marcuzzuu.pokeshakeapi.client;

import it.marcuzzuu.pokeshakeapi.model.pokeapi.PokemonSpecies;

import java.util.Optional;

public interface IPokeApi
{
	Optional<PokemonSpecies> getSpecies(final String name);
}
