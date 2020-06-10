package it.marcuzzuu.pokeshakeapi.controller;

import it.marcuzzuu.pokeshakeapi.model.ErrorResponse;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.service.impl.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.SocketException;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PokemonController
{
	private PokemonService pokemonService;

	@Autowired
	public PokemonController(final PokemonService pokemonService)
	{
		this.pokemonService = pokemonService;
	}

	@GetMapping("/{name}")
	public ResponseEntity getDescriptionByName(@PathVariable(name = "name") final String name)
	{
		if (!StringUtils.isEmpty(name))
		{
			final Optional<PokemonDescription> description = this.pokemonService.retrieveDescription(name, null);
			if (description.isPresent())
			{
				return ResponseEntity.ok(description.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), String.format("No description found for pokemon: %s", name)));
		}
		return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "A name must be provided"));
	}

	@ExceptionHandler({SocketException.class, NullPointerException.class})
	public ResponseEntity<ErrorResponse> handleException()
	{
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong with your request"));
	}
}
