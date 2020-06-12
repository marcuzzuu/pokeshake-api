package it.marcuzzuu.pokeshakeapi.model.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorTextEntry
{
	@JsonProperty("flavor_text")
	private String flavorText;
	private Language language;
}
