package it.marcuzzuu.pokeshakeapi.model.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class PokemonSpecies
{
	@JsonProperty("flavor_text_entries")
	private List<FlavorTextEntry> flavorTextEntries;
}
