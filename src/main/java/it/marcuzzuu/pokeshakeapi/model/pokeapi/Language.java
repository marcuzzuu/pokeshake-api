package it.marcuzzuu.pokeshakeapi.model.pokeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Language
{
	private String name;
}
