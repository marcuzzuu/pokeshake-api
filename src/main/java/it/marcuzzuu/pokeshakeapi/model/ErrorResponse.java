package it.marcuzzuu.pokeshakeapi.model;

import lombok.Data;

@Data
public class ErrorResponse
{
	private final int statusCode;
	private final String error;
}
