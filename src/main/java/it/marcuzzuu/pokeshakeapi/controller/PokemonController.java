package it.marcuzzuu.pokeshakeapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.marcuzzuu.pokeshakeapi.model.ErrorResponse;
import it.marcuzzuu.pokeshakeapi.model.PokemonDescription;
import it.marcuzzuu.pokeshakeapi.service.impl.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/pokemon")
@Tag(name="Pokemon", description = "Pokemon resource endpoint")
public class PokemonController
{
	private PokemonService pokemonService;

	@Autowired
	public PokemonController(final PokemonService pokemonService)
	{
		this.pokemonService = pokemonService;
	}

	@GetMapping("/{name}")
	@Operation(summary = "Get a pokemon description in a Shakespearean dialect by its name")
	@ApiResponses
	(
		{
			@ApiResponse(responseCode = "200", description = "Found description", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonDescription.class))}),
			@ApiResponse(responseCode = "400", description = "Invalid name", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "404", description = "Description not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PokemonDescription.class))}),
			@ApiResponse(responseCode = "429", description = "Too many requests", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
			@ApiResponse(responseCode = "500", description = "Generic server error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
		}
	)
	public ResponseEntity getDescriptionByName(@Parameter(description = "Name of pokemon to be searched")@PathVariable(name = "name") final String name)
	{
		if (!StringUtils.isEmpty(name))
		{
			final Optional<PokemonDescription> description = this.pokemonService.retrieveDescription(name, null);
			if (description.isPresent() && !StringUtils.isEmpty(description.get().getDescription()))
			{
				return ResponseEntity.ok(description.get());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PokemonDescription(name, ""));
		}
		return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "A name must be provided"));
	}

	@ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
	public ResponseEntity<ErrorResponse> handleTooManyRequestsError(final HttpClientErrorException.TooManyRequests ex)
	{
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), "Too many requests. Try again later."));
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleGenericError(final Exception ex)
	{
		log.error("Error occurred", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), String.format("Something went wrong with your request: %s", ex.getMessage())));
	}
}
