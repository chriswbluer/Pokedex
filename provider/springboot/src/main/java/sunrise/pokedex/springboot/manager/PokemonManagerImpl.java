package sunrise.pokedex.springboot.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sunrise.pokedex.springboot.view.PokemonViewImpl;
import sunrise.pokedex.springboot.model.Pokemon;
import sunrise.pokedex.springboot.service.PokemonService;

@Service
public class PokemonManagerImpl implements PokemonManager {

    @Autowired
	private PokemonService pokemonService;

	public PokemonViewImpl convertToView(Pokemon pokemon) {
		PokemonViewImpl pokemonView = new PokemonViewImpl();
		BeanUtils.copyProperties(pokemon, pokemonView);
		return pokemonView;
	}
	
	public Pokemon convertToImpl(PokemonViewImpl pokemon) {
		Pokemon pokemonImpl = new Pokemon();
		BeanUtils.copyProperties(pokemon, pokemonImpl);
		return pokemonImpl;
	}

    @Override
    public PokemonViewImpl findById(Long id) {
        Pokemon pokemonImpl = pokemonService.findById(id);
        PokemonViewImpl pokemonView = convertToView(pokemonImpl);
        return pokemonView;
    }

    @Override
    public PokemonViewImpl findByUsername(String name) {
        Pokemon pokemonImpl = pokemonService.findByName(name);
        PokemonViewImpl pokemonView = convertToView(pokemonImpl);
        return pokemonView;
    }

    @Override
    public PokemonViewImpl savePokemon(PokemonViewImpl pokemon) {
        Pokemon pokemonImpl = convertToImpl(pokemon);
		Pokemon servicePokemonImpl = pokemonService.savePokemon(pokemonImpl);
		PokemonViewImpl pokemonView = convertToView(servicePokemonImpl);
		return pokemonView;
    }

    @Override
    public PokemonViewImpl updatePokemon(PokemonViewImpl pokemon) {
        Pokemon pokemonImpl = convertToImpl(pokemon);
		Pokemon updatedPokemonImpl = pokemonService.updatePokemon(pokemonImpl);
		PokemonViewImpl pokemonView = convertToView(updatedPokemonImpl);
		return pokemonView;
    }

    @Override
    public Long deletePokemonById(Long id) {
        return pokemonService.deletePokemonById(id);
    }

    @Override
    public List<PokemonViewImpl> findAllPokemons() {
        List<Pokemon> pokemonList = pokemonService.findAllPokemons();
		List<PokemonViewImpl> pokemonViewList = new ArrayList<PokemonViewImpl>();
		for (Pokemon pokemon : pokemonList) {
			PokemonViewImpl pokemonView = new PokemonViewImpl();
			BeanUtils.copyProperties(pokemon, pokemonView);
			pokemonViewList.add(pokemonView);
		}
		return pokemonViewList;
    }

    @Override
    public List<Pokemon> deleteAllPokemons() {
        return pokemonService.deleteAllPokemons();
    }

    @Override
    public Boolean isPokemonExist(PokemonViewImpl pokemon) {
        Pokemon pokemonImpl = convertToImpl(pokemon);
		return pokemonService.isPokemonExist(pokemonImpl);
    }
    
}
