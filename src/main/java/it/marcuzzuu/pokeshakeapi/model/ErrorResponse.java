package it.marcuzzuu.pokeshakeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse
{
	private int statusCode;
	private String error;
}
