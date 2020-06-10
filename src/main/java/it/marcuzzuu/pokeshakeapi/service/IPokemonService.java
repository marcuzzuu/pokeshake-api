package it.marcuzzuu.pokeshakeapi.service;

import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;

import java.util.Optional;

public interface IPokemonService
{
	Optional<PokemonDescription> retrieveDescription(final String name, final String translateTo);
}
