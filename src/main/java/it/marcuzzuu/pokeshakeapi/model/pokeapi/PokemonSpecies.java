package it.marcuzzuu.pokeshakeapi.model.pokeapi;

import lombok.Data;

import java.util.List;

@Data
public class PokemonSpecies
{
	private List<FlavorTextEntry> flavorTextEntries;
}
