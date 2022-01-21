package com.pokedex.springmvc.service;

import java.util.List;
import com.pokedex.springmvc.model.Pokemon;

public interface PokemonService {

    Pokemon findById(long id);
	
	Pokemon findByName(String name);
	
	void savePokemon(Pokemon pokemon);
	
	void updatePokemon(Pokemon pokemon);
	
	void deletePokemonById(long id);

	List<Pokemon> findAllPokemons(); 
	
	void deleteAllPokemons();
	
	public boolean isPokemonExist(Pokemon pokemon);
    
}
