package it.marcuzzuu.pokeshakeapi.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public abstract class ApiClient
{
	public static final MultiValueMap<String, String> DEFAULT_HEADERS;
	protected final RestTemplate restTemplate;

	static
	{
		DEFAULT_HEADERS = new LinkedMultiValueMap<>();
		DEFAULT_HEADERS.add(HttpHeaders.USER_AGENT, "Mozilla/5.0");
		DEFAULT_HEADERS.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}

	protected ApiClient(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
}
