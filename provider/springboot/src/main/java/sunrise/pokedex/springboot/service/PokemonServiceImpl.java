package sunrise.pokedex.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sunrise.pokedex.springboot.dao.PokemonDao;
import sunrise.pokedex.springboot.model.Pokemon;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonDao pokemonDao;

	public Pokemon findById(Long id) {
		if (Optional.ofNullable(id).isPresent()) {
			return pokemonDao.findById(id);
		} else {
			throw new RuntimeException();
		}
	}

	public Pokemon findByName(String name) {
		return pokemonDao.findByName(name);
	}

	public Pokemon savePokemon(Pokemon pokemon) {
		if (pokemon != null) {
			return pokemonDao.savePokemon(pokemon);	
		} else {
			throw new RuntimeException();
		}
	}

	public Pokemon updatePokemon(Pokemon pokemon) {
		return pokemonDao.updatePokemon(pokemon);
	}

	public Long deletePokemonById(Long id) {
		if (Optional.ofNullable(id).isPresent()) {
			return pokemonDao.deletePokemonById(id);
		} else {
			throw new RuntimeException();
		}
	}

	public List<Pokemon> findAllPokemons() {
		return pokemonDao.findAllPokemons();
	}

	public List<Pokemon> deleteAllPokemons() {
		return pokemonDao.deleteAllPokemons();
	}

	public Boolean isPokemonExist(Pokemon pokemon) {
		return pokemonDao.isPokemonExist(pokemon);
	}

}
