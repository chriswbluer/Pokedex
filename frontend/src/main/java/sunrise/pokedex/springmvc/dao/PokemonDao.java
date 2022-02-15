package sunrise.pokedex.springmvc.dao;

import java.util.List;

import sunrise.pokedex.springmvc.model.Pokemon;

public interface PokemonDao {
    
    Pokemon findById(Long id);
	
	Pokemon findByName(String name);
	
	Pokemon savePokemon(Pokemon pokemon);
	
	Pokemon updatePokemon(Pokemon pokemon);
	
	Long deletePokemonById(Long id);

	List<Pokemon> findAllPokemons(); 
	
	List<Pokemon> deleteAllPokemons();

	Boolean isPokemonExist(Pokemon pokemon);
    
}
