package com.pokedex.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.pokedex.springmvc.model.Pokemon;

import org.springframework.stereotype.Service;

@Service("pokemonService")
public class PokemonServiceImpl implements PokemonService {

    private static final AtomicLong counter = new AtomicLong();
	
	private static List<Pokemon> pokemons;
	
	static{
		pokemons = populateDummyPokemons();
	}

	public List<Pokemon> findAllPokemons() {
		return pokemons;
	}
	
	public Pokemon findById(long id) {
		for(Pokemon pokemon : pokemons){
			if(pokemon.getId() == id){
				return pokemon;
			}
		}
		return null;
	}
	
	public Pokemon findByName(String name) {
		for(Pokemon pokemon : pokemons){
			if(pokemon.getName().equalsIgnoreCase(name)){
				return pokemon;
			}
		}
		return null;
	}
	
	public void savePokemon(Pokemon pokemon) {
		pokemon.setId(counter.incrementAndGet());
		pokemons.add(pokemon);
	}

	public void updatePokemon(Pokemon pokemon) {
		int index = pokemons.indexOf(pokemon);
		pokemons.set(index, pokemon);
	}

	public void deletePokemonById(long id) {
		
		for (Iterator<Pokemon> iterator = pokemons.iterator(); iterator.hasNext(); ) {
		    Pokemon pokemon = iterator.next();
		    if (pokemon.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isPokemonExist(Pokemon pokemon) {
		return findByName(pokemon.getName())!=null;
	}
	
	public void deleteAllPokemons(){
		pokemons.clear();
	}

	private static List<Pokemon> populateDummyPokemons(){
		List<Pokemon> Pokemons = new ArrayList<Pokemon>();
		Pokemons.add(new Pokemon(counter.incrementAndGet(),"Bulbasaur", 20, 40));
		Pokemons.add(new Pokemon(counter.incrementAndGet(),"Charmander", 15, 30));
		Pokemons.add(new Pokemon(counter.incrementAndGet(),"Squirtle", 12, 20));
		return Pokemons;
	}
    
}
