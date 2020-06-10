package it.marcuzzuu.pokeshakeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PokeshakeApiApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(PokeshakeApiApplication.class, args);
	}

}
