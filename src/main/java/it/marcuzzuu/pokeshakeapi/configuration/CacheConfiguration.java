package it.marcuzzuu.pokeshakeapi.configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

@Configuration("cacheConfiguration")
public class CacheConfiguration
{
	@Bean("pokemonsCache")
	public Cache<String, Integer> pokemonsCache(@Value("${cache.pokemons.capacity:10000}") final Integer maxCapacity, @Value("${cache.pokemons.expire-after-seconds:120}") final Integer expireAfterSeconds)
	{
		return Caffeine.newBuilder().maximumSize(maxCapacity).expireAfterWrite(expireAfterSeconds, TimeUnit.SECONDS).build();
	}
}
