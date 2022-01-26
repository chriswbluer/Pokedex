package sunrise.pokedex.springmvc.service;

import java.util.List;

import sunrise.pokedex.springmvc.model.Pokemon;

public interface PokemonService {

    Pokemon findById(long id);
	
	Pokemon findByName(String name);
	
	Pokemon savePokemon(Pokemon pokemon);
	
	Pokemon updatePokemon(Pokemon pokemon);
	
	Long deletePokemonById(long id);

	List<Pokemon> findAllPokemons(); 
	
	List<Pokemon> deleteAllPokemons();
	
	boolean isPokemonExist(Pokemon pokemon);
    
}
