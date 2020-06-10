package it.marcuzzuu.pokeshakeapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class ApiClient
{
	@Autowired
	protected RestTemplate restTemplate;
}
