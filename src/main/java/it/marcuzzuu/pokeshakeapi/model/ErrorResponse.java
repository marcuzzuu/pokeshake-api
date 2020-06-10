package it.marcuzzuu.pokeshakeapi.model;

import lombok.Data;

@Data
public class ErrorResponse
{
	private String error;
	private int statusCode;
}
