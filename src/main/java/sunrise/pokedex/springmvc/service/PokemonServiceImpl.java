package sunrise.pokedex.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sunrise.pokedex.springmvc.dao.PokemonDao;
import sunrise.pokedex.springmvc.model.Pokemon;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonDao pokemonDao;

	public Pokemon findById(Long id) {
		return pokemonDao.findById(id);
	}

	public Pokemon findByName(String name) {
		return pokemonDao.findByName(name);
	}

	@Transactional
	public Pokemon savePokemon(Pokemon pokemon) {
		return pokemonDao.savePokemon(pokemon);
	}

	@Transactional
	public Pokemon updatePokemon(Pokemon pokemon) {
		return pokemonDao.updatePokemon(pokemon);
	}

	@Transactional
	public Long deletePokemonById(Long id) {
		return pokemonDao.deletePokemonById(id);
	}

	public List<Pokemon> findAllPokemons() {
		return pokemonDao.findAllPokemons();
	}

	@Transactional
	public List<Pokemon> deleteAllPokemons() {
		return pokemonDao.deleteAllPokemons();
	}

	public Boolean isPokemonExist(Pokemon pokemon) {
		return pokemonDao.isPokemonExist(pokemon);
	}

}
