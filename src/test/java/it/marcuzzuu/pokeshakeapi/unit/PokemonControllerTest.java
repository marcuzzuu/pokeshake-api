package it.marcuzzuu.pokeshakeapi.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.marcuzzuu.pokeshakeapi.controller.PokemonController;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.service.impl.PokemonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest
{
	@Autowired
	private MockMvc mvc;
	@MockBean
	private PokemonService pokemonService;
	@Autowired
	private ObjectMapper mapper;

	@Test
	void getDescriptionWithValidNameShouldReturnShakespeareanDescriptionAnd200() throws Exception
	{
		final String name = "charizard";
		final String description = "Charizard flies 'round the sky in search of powerful opponents. " +
				"'t breathes fire of such most wondrous heat yond 't melts aught. " +
				"However, 't nev'r turn its fiery breath on any opponent weaker than itself.";
		final PokemonDescription fullPokemonDescription = new PokemonDescription(name, description);
		given(this.pokemonService.retrieveDescription(name, null)).willReturn(Optional.of(fullPokemonDescription));
		this.mvc.perform(get("/pokemon/" + name)).andExpect(status().isOk()).andExpect(content().json(this.mapper.writeValueAsString(fullPokemonDescription)));
	}

	@Test
	void getDescriptionWithInValidNameShouldReturnErrorResponseAnd404() throws Exception
	{
		final String name = "!charizard!";
		given(this.pokemonService.retrieveDescription(name, null)).willReturn(Optional.of(new PokemonDescription(name, null)));
		this.mvc.perform(get("/pokemon/" + name)).andExpect(status().isNotFound()).andExpect(jsonPath("name").value(name)).andExpect(jsonPath("description").isEmpty());
	}

	@Test
	void getDescriptionWithNoNameShouldReturn404() throws Exception
	{
		final String name = "";
		this.mvc.perform(get("/pokemon/" + name)).andExpect(status().isNotFound());
	}
}
