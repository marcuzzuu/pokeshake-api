package it.marcuzzuu.pokeshakeapi.controller;

import it.marcuzzuu.pokeshakeapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class PokemonController
{
	@GetMapping("/{name}")
	public ResponseEntity getDescriptionByName(@PathVariable(name = "name") final String name)
	{
		if (!StringUtils.isEmpty(name))
		{
			return null;
		}
		return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "A name must be provided"));
	}
}
