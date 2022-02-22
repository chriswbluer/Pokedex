package sunrise.pokedex.springboot.manager;

import java.util.List;

import sunrise.pokedex.springboot.model.Pokemon;
import sunrise.pokedex.springboot.view.PokemonViewImpl;

public interface PokemonManager {
    PokemonViewImpl findById(Long id);
	
	PokemonViewImpl findByUsername(String username);
	
	PokemonViewImpl savePokemon(PokemonViewImpl pokemon);
	
	PokemonViewImpl updatePokemon(PokemonViewImpl pokemon);
	
	Long deletePokemonById(Long id);

	List<PokemonViewImpl> findAllPokemons(); 
	
	List<Pokemon> deleteAllPokemons();
	
	Boolean isPokemonExist(PokemonViewImpl pokemon);
}
