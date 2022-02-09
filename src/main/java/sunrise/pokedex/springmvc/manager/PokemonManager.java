package sunrise.pokedex.springmvc.manager;

import java.util.List;

import sunrise.pokedex.springmvc.model.Pokemon;
import sunrise.pokedex.springmvc.view.PokemonViewImpl;

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
