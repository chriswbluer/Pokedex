package sunrise.pokedex.springboot.dao;

import java.util.List;

import sunrise.pokedex.springboot.model.Pokemon;

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
